package net.zithium.library.utils;

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
}
