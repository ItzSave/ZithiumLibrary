package net.zithium.library.version;

public class AdventureCheck {

    /**
     * Checks whether the MiniMessage library is compatible and available at runtime.
     *
     * @return {@code true} if MiniMessage is compatible and available; {@code false} otherwise.
     */
    public static boolean isMiniMessageCompatible(){
        try {
            Class.forName("net.kyori.adventure.text.minimessage.MiniMessage");
            return true;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }
}
