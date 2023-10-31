package net.zithium.library.version;


import org.bukkit.Bukkit;

import java.util.logging.Level;

public class AdventureCheck {

    /**
     * Checks whether the MiniMessage library is compatible and available at runtime.
     *
     * @return {@code true} if MiniMessage is compatible and available; {@code false} otherwise.
     */
    public static boolean isMiniMessageCompatible() {
            try {
                Class.forName("net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;");
                Bukkit.getServer().getLogger().log(Level.INFO, "Server does not support adventure using legacy.");
                return true;
            } catch (ClassNotFoundException ex) {
                return false;
            }
        }
}
