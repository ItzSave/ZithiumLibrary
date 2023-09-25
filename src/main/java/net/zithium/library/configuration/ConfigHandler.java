package net.zithium.library.configuration;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Utility class for managing and creating YAML configuration files.
 */
@SuppressWarnings("unused")
public class ConfigHandler {

    private final JavaPlugin plugin;
    private final String name;
    private final File file;
    private FileConfiguration configuration;

    /**
     * Constructs a new ConfigHandler.
     *
     * @param plugin The JavaPlugin instance that owns this configuration.
     * @param name   The name of the YAML configuration file (excluding ".yml").
     */
    public ConfigHandler(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name + ".yml";
        this.file = new File(plugin.getDataFolder(), this.name);
        this.configuration = new YamlConfiguration();
    }

    /**
     * Saves the default configuration if the file does not exist.
     * Displays a warning and disables the plugin if there's an error loading the configuration.
     */
    public void saveDefaultConfig() {
        if (!file.exists()) {
            plugin.saveResource(name, false);
        }

        try {
            configuration.load(file);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
            plugin.getLogger().severe("============= CONFIGURATION ERROR =============");
            plugin.getLogger().severe("There was an error loading " + name);
            plugin.getLogger().severe("Please check for any obvious configuration mistakes");
            plugin.getLogger().severe("such as using tabs for spaces or forgetting to end quotes");
            plugin.getLogger().severe("before reporting to the developer. The plugin will now disable..");
            plugin.getLogger().severe("============= CONFIGURATION ERROR =============");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }

    /**
     * Saves the configuration to the file.
     */
    public void save() {
        if (configuration == null || file == null) return;
        try {
            getConfig().save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reloads the configuration from the file.
     */
    public void reload() {
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Gets the FileConfiguration associated with this ConfigHandler.
     *
     * @return The FileConfiguration.
     */
    public FileConfiguration getConfig() {
        return configuration;
    }

    /**
     * Gets the File object representing the configuration file.
     *
     * @return The File object.
     */
    public File getFile() {
        return file;
    }
}
