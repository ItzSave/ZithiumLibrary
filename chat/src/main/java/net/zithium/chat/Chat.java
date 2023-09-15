package net.zithium.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.plugin.java.JavaPlugin;

public final class Chat extends JavaPlugin {


    /**
     * This function will color any text messages.
     * @param message The target message to be colored
     * @return The colored message
     */
    public static String color(String message) {
        Component component = MiniMessage.miniMessage().deserialize(message);
        return LegacyComponentSerializer.legacySection().serialize(component);
    }

}
