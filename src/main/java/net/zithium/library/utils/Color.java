/**
 * This class provides utility methods for colorizing text messages in Minecraft.
 * It allows you to convert legacy color codes (e.g., "&1", "&a") into Adventure
 * components for creating colored text.
 */
package net.zithium.library.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.plugin.java.JavaPlugin;

public final class Color extends JavaPlugin {

    /**
     * Colorizes a text message containing legacy color codes.
     *
     * @param message The text message with legacy color codes.
     * @return The colorized message as a string.
     */
    public static String color(String message) {
        Component componentMessage = MiniMessage.miniMessage().deserialize(replaceLegacy(message));
        return LegacyComponentSerializer.legacySection().serialize(componentMessage);
    }

    /**
     * Replaces legacy color codes (e.g., "&1" for dark blue) with Adventure format codes
     * (e.g., "<dark_blue>").
     *
     * @param legacyText The text containing legacy color codes.
     * @return The text with Adventure format codes.
     */
    private static String replaceLegacy(String legacyText) {
        return legacyText
                .replaceAll("&1", "<dark_blue>")
                .replaceAll("&2", "<dark_green>")
                .replaceAll("&3", "<dark_aqua>")
                .replaceAll("&4", "<dark_red>")
                .replaceAll("&5", "<dark_purple>")
                .replaceAll("&6", "<gold>")
                .replaceAll("&7", "<gray>")
                .replaceAll("&8", "<dark_gray>")
                .replaceAll("&9", "<blue>")
                .replaceAll("&a", "<green>")
                .replaceAll("&b", "<aqua>")
                .replaceAll("&c", "<red>")
                .replaceAll("&d", "<light_purple>")
                .replaceAll("&e", "<yellow>")
                .replaceAll("&f", "<white>")
                .replaceAll("&l", "<bold>")
                .replaceAll("&k", "<obfuscated>")
                .replaceAll("&m", "<strikethrough>")
                .replaceAll("&n", "<underline>")
                .replaceAll("&r", "<reset>")
                .replaceAll("&o", "<italic>");
    }
}