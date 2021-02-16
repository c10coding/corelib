package net.dohaw.corelib;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JPUtils {

    private static JavaPlugin instance = CoreLib.getInstance();

    public static void validateFiles(String... fileNames){
        for(String name : fileNames){
            File file = new File(instance.getDataFolder(), name);
            if(!file.exists()){
                instance.saveResource(name, false);
            }
        }
    }

    /*
        For if you want to either validate a folder, or validate a resource that needs to be saved in a directory other than the plugin config directory
     */
    public static void validateFilesOrFolders(Map<String, Object> info, boolean isFolder){
        for(Map.Entry<String, Object> entry : info.entrySet()){
            String fileName = entry.getKey();
            File path = (File) entry.getValue();
            File file = new File(path, fileName);
            if(!file.exists()){
                if(isFolder){
                    file.mkdirs();
                }else{
                    InputStream resource = instance.getResource(fileName);
                    if(resource != null){

                        boolean fileHasBeenCreated = false;
                        try {
                            fileHasBeenCreated = file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if(fileHasBeenCreated) {

                            try {
                                FileUtils.copyInputStreamToFile(resource, file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }
            }


        }
    }



    public static void registerCommand(String commandName, CommandExecutor exec){
        instance.getServer().getPluginCommand(commandName).setExecutor(exec);
    }

    public static void registerEvents(Listener... listeners){
        JavaPlugin plugin = CoreLib.getInstance();
        for(Listener l : listeners){
            Bukkit.getServer().getPluginManager().registerEvents(l, plugin);
        }
    }

}
