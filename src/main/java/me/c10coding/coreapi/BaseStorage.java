package me.c10coding.coreapi;

import me.c10coding.coreapi.chat.ChatFactory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


/*
    Carries instances of important variables that you need throughout your project.
 */
public class BaseStorage {

    public JavaPlugin plugin;
    public CoreAPI api;
    public ChatFactory chatFactory;
    public Logger logger;

    /*
        For those that want to use this without using BetterJavaPlugin hook.
     */
    public BaseStorage(JavaPlugin plugin){
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    public BaseStorage(BetterJavaPlugin plugin){
        this.plugin = plugin;
        this.api = plugin.getAPI();
        this.chatFactory = api.getChatFactory();
        this.logger = plugin.getLogger();
    }

}
