package net.zithium.library;

import org.bukkit.Bukkit;

public class PlatformChecker {



    /**
     * Checks to see if the server is running Paper or a fork of Paper.
     */
    public boolean checkForPaper() {
        if (!hasClass("com.destroystokyo.paper.PaperConfig") || !hasClass("io.papermc.paper.configuration.Configuration")) {
            Bukkit.getLogger().severe("---- [ PLUGIN WARNING ] ----");
            Bukkit.getLogger().severe("You are not running Paper or a fork of paper");
            Bukkit.getLogger().severe("We highly suggest using paper to get the full");
            Bukkit.getLogger().severe("experience with our plugins!");
            Bukkit.getLogger().severe("");
            Bukkit.getLogger().severe("You can find paper here: https://papermc.io/downloads/paper");
            Bukkit.getLogger().severe("---- [ PLUGIN SHUTDOWN ] ----");
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param className The class to check for
     * @return Whether that class was found.
     */
    private static boolean hasClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
