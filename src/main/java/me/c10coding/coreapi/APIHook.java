package me.c10coding.coreapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class APIHook extends JavaPlugin {

    public CoreAPI hookAPI(JavaPlugin plugin){
        if(getServer().getPluginManager().getPlugin("CoreAPI") == null){
            plugin.getLogger().severe("Disabling this plugin. Could not find CoreAPI dependency!");
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
            return null;
        }
        return (CoreAPI) getServer().getPluginManager().getPlugin("CoreAPI");
    }

}
