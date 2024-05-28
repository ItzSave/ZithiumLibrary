package net.zithium.library.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.List;
import java.util.regex.Pattern;

/**
 * A utility class that contains various methods to format text in various ways.
 */
public class TextUtils {

    /**
     * A pattern for stripping color codes from a text string.
     */
    private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + '&' + "[0-9A-FK-OR]");

    private static final Pattern COLOR_PATTERN = Pattern.compile("(?i)§[0-9A-FK-OR]");
    private static final Pattern CUSTOM_PATTERN = Pattern.compile("<[^>]+>");

    private static final int TOTAL_WIDTH = 60; // Assuming the Minecraft chat width is 60

    /**
     * Removes color codes from a given input string.
     *
     * @param input The input string containing color codes.
     * @return The input string with color codes removed.
     */
    public static String stripColor(String input) {
        return input == null ? null : STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }

    /**
     * Creates a formatted string from a list of objects. Color codes are stripped, and
     * the "&lt;reset&gt;" tag is inserted before an empty string in the list.
     *
     * @param list The list of objects to format into a string.
     * @return The formatted string.
     */
    public static String fromList(List<?> list) {
        if (list == null || list.isEmpty()) return null;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            if (stripColor(list.get(i).toString()).equals("")) builder.append("\n<reset>");
            else builder.append(list.get(i).toString()).append(i + 1 != list.size() ? "\n" : "");
        }

        return builder.toString();
    }

    /**
     * Centers the provided text in Minecraft chat.
     *
     * @param text The text to be centered.
     * @return The centered text as a Component.
     */
    public static Component centerText(String text) {
        if (text.contains("<center>")) {
            // Calculate the width of the text without color codes
            int strippedLength = stripColorAndCustomCodes(text).length();

            // Calculate the spaces needed for centering
            int spacesNeeded = Math.max(0, (TOTAL_WIDTH - strippedLength) / 2);

            // Construct the centered text
            TextComponent.Builder centeredText = Component.text();
            for (int i = 0; i < spacesNeeded; i++) {
                centeredText.append(Component.text(" "));
            }
            centeredText.append(MiniMessage.miniMessage().deserialize(text.replace("<center>", "")));

            return centeredText.build();
        } else {
            return MiniMessage.miniMessage().deserialize(text);
        }
    }




    // Helper method to strip color and custom codes
    private static String stripColorAndCustomCodes(String text) {
        // Remove Minecraft color codes
        String strippedText = COLOR_PATTERN.matcher(text).replaceAll("");

        // Remove custom codes
        strippedText = CUSTOM_PATTERN.matcher(strippedText).replaceAll("");

        // Replace legacy formatting codes with modern formatting tags
        return ColorUtil.replaceLegacyWithTags(strippedText);
    }

}
