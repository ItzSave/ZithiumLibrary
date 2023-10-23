package net.zithium.library.version;

import org.bukkit.Bukkit;

public class AdventureCheck {

    /**
     * Checks whether the MiniMessage library is compatible and available at runtime.
     *
     * @return {@code true} if MiniMessage is compatible and available; {@code false} otherwise.
     */
    public static boolean isMiniMessageCompatible() {

        if (Bukkit.getVersion().contains("1.18.2")) {
            try {
                Class.forName("net.kyori.adventure.text.minimessage.MiniMessage");
                return true;
            } catch (ClassNotFoundException ex) {
                return false;
            }
        } else {
            return false;
        }
    }
}
