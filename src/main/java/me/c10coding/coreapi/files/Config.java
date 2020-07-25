package me.c10coding.coreapi.files;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class Config  {

    protected Logger logger;
    protected String fileName;
    protected File file;
    protected FileConfiguration config = new YamlConfiguration();
    protected JavaPlugin plugin;

    public Config(JavaPlugin plugin, String fileName){
        this.plugin = plugin;
        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);
        this.logger = plugin.getLogger();
        loadConfig();
    }

    public void saveConfig(){
        try {
            config.save(file);
        }catch(IOException e) {
            plugin.getLogger().warning("Unable to save " + fileName);
        }
    }

    public void loadConfig(){
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            logger.warning("Unable to load " + fileName);
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig(){
        return config;
    }

    public List<String> getList(String path){
        return config.getStringList(path);
    }

    public String getValue(String path) {
        return config.getString(path);
    }

}
