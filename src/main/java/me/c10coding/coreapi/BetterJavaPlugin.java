package me.c10coding.coreapi;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BetterJavaPlugin extends APIHook{

    public BaseStorage storage = new BaseStorage(this);

    public void validateFiles(String... fileNames){
        for(String name : fileNames){
            File file = new File(this.getDataFolder(), name);
            if(!file.exists()){
                saveResource(name, false);
            }
        }
    }

    public void validateFolders(HashMap<String, Object> folderInfo){
        for(Map.Entry<String, Object> info : folderInfo.entrySet()){
            String fileName = info.getKey();
            String path = (String) info.getValue();
            File file = new File(path, fileName);
            if(!file.exists()){
                file.mkdirs();
            }
        }
    }

    public void registerCommand(String commandName, CommandExecutor exec){
        getServer().getPluginCommand(commandName).setExecutor(exec);
    }

    public void registerEvents(Listener... listeners){
        for(Listener l : listeners){
            getServer().getPluginManager().registerEvents(l, this);
        }
    }

}
