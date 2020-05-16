//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.c10coding.coreapi;

import me.c10coding.coreapi.chat.Chat;
import me.c10coding.coreapi.helpers.MathHelper;
import me.c10coding.coreapi.helpers.PlayerHelper;
import me.c10coding.coreapi.holograms.HologramHelper;
import me.c10coding.coreapi.serializers.LocationSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CoreAPI {

    private Chat chatFactory;
    private MathHelper mathHelper;
    private PlayerHelper playerHelper;

    public CoreAPI() {
        this.chatFactory = new Chat();
        this.mathHelper = new MathHelper();
        this.playerHelper = new PlayerHelper();
    }

    public LocationSerializer createLocationSerializer(FileConfiguration config){
        return new LocationSerializer(config);
    }

    public Chat getChatFactory(){
        return chatFactory;
    }

    public MathHelper getMathHelper(){
        return mathHelper;
    }

    public PlayerHelper getPlayerHelper(){
        return playerHelper;
    }

    public HologramHelper getHologramHelper(JavaPlugin plugin){
        return new HologramHelper(plugin);
    }


}
