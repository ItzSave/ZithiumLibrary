
package net.zithium.library.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.zithium.library.version.AdventureCheck;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class provides utility methods for colorizing text messages in Minecraft.
 * It allows you to convert legacy color codes (e.g., "&1", "&a") into Adventure
 * components for creating colored text.
 */
public final class Color extends JavaPlugin {

    /**
     * Colorizes a text message containing legacy color codes or MiniMessage formatting.
     *
     * <p>If MiniMessage is compatible, this method converts the provided message with MiniMessage
     * and then serializes it to a legacy string format. If MiniMessage is not available, it
     * falls back to translating legacy color codes using ChatColor.</p>
     *
     * @param message The text message with legacy color codes or MiniMessage formatting.
     * @return The colorized message as a plain string.
     */
    public static String stringColor(String message) {
        if (AdventureCheck.isMiniMessageCompatible()) {
            Component componentMessage = MiniMessage.miniMessage().deserialize(message);
            return LegacyComponentSerializer.legacySection().serialize(componentMessage);
        } else {
            return ChatColor.translateAlternateColorCodes('&', message);
        }
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
