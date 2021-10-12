package net.dohaw.corelib;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        createFile();
        loadConfig();
    }

    public Config(JavaPlugin plugin, File file){
        this.plugin = plugin;
        this.fileName = file.getName();
        this.file = file;
        this.logger = plugin.getLogger();
        createFile();
        loadConfig();
    }

    /*
        Only use the two constructors below if you are hooking into CoreLib.
     */
    public Config(String fileName){
        this.plugin = CoreLib.getInstance();
        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);
        this.logger = plugin.getLogger();
        createFile();
        loadConfig();
    }

    public Config(File file){
        this.plugin = CoreLib.getInstance();
        this.fileName = file.getName();
        this.file = file;
        this.logger = plugin.getLogger();
        createFile();
        loadConfig();
    }

    public void saveConfig(){
        try {
            config.save(file);
        }catch(IOException e) {
            logger.warning("Unable to save " + fileName);
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

    private void createFile(){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public List<String> getCommaList(String path){

        String line = config.getString(path);
        String[] arr = line.split(",");

        List<String> list = new ArrayList<>();
        for(String s : arr){
            list.add(s.trim());
        }

        return list;
    }

}
