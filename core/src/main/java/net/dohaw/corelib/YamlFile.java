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

public class YamlFile {

    protected Logger logger;
    protected String fileName;
    protected File file;
    protected FileConfiguration loadedFile = new YamlConfiguration();
    protected JavaPlugin plugin;

    public YamlFile(JavaPlugin plugin, String fileName){
        this.plugin = plugin;
        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);
        this.logger = plugin.getLogger();
        createFile();
        load();
    }

    public YamlFile(JavaPlugin plugin, File file){
        this.plugin = plugin;
        this.fileName = file.getName();
        this.file = file;
        this.logger = plugin.getLogger();
        createFile();
        load();
    }

    /*
        Only use the two constructors below if you are hooking into CoreLib.
     */
    public YamlFile(String fileName){
        this.plugin = CoreLib.getInstance();
        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);
        this.logger = plugin.getLogger();
        createFile();
        load();
    }

    public YamlFile(File file){
        this.plugin = CoreLib.getInstance();
        this.fileName = file.getName();
        this.file = file;
        this.logger = plugin.getLogger();
        createFile();
        load();
    }

    public void save(){
        try {
            loadedFile.save(file);
        }catch(IOException e) {
            logger.warning("Unable to save " + fileName);
        }
    }

    private void load(){
        try {
            loadedFile.load(file);
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

    public void reload() {
        loadedFile = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getLoadedFile(){
        return loadedFile;
    }

    public List<String> getList(String path){
        return loadedFile.getStringList(path);
    }

    public String getValue(String path) {
        return loadedFile.getString(path);
    }

    public List<String> getCommaList(String path){

        String line = loadedFile.getString(path);
        String[] arr = line.split(",");

        List<String> list = new ArrayList<>();
        for(String s : arr){
            list.add(s.trim());
        }

        return list;
    }

}
