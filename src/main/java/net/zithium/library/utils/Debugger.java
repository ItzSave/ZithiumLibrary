package net.zithium.library.utils;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Debugger {

    private static Logger logger;

    public static void setLogger(JavaPlugin plugin) {
        logger = plugin.getLogger();
    }

    public static void log(String message, JavaPlugin plugin) {
        if (logger != null) {
            logger.info("[DEBUG][" + plugin.getName() + "] " + message);
        }
    }
}