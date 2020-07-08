package me.c10coding.coreapi;

import org.bukkit.plugin.java.JavaPlugin;

public class APIHook extends JavaPlugin {

    private CoreAPI api;

    public void hookAPI(JavaPlugin plugin){
        if(plugin.getServer().getPluginManager().getPlugin("CoreAPI") == null){
            plugin.getLogger().severe("Disabling this plugin. Could not find CoreAPI dependency!");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        this.api = (CoreAPI) plugin.getServer().getPluginManager().getPlugin("CoreAPI");
    }

    public CoreAPI getAPI(){
        return api;
    }

}
