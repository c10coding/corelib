//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.c10coding.coreapi;

import me.c10coding.coreapi.chat.Chat;
import me.c10coding.coreapi.helpers.MathHelper;
import me.c10coding.coreapi.helpers.PlayerHelper;
import me.c10coding.coreapi.helpers.ProbabilityUtilities;
import me.c10coding.coreapi.holograms.HologramHelper;
import me.c10coding.coreapi.serializers.ItemStackSerializer;
import me.c10coding.coreapi.serializers.LocationSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CoreAPI extends JavaPlugin{

    @Override
    public void onEnable(){ }

    @Override
    public void onDisable(){}

    public LocationSerializer createLocationSerializer(FileConfiguration config){
        return new LocationSerializer(config);
    }

    public Chat getChatFactory(){
        return new Chat();
    }

    public MathHelper getMathHelper(){
        return new MathHelper();
    }

    public PlayerHelper getPlayerHelper(){
        return new PlayerHelper();
    }

    public HologramHelper getHologramHelper(JavaPlugin plugin){
        return new HologramHelper(plugin);
    }

    public ProbabilityUtilities getProbabilityUtilities(){
        return new ProbabilityUtilities();
    }

    public ItemStackSerializer getItemStackSerializer(){
        return new ItemStackSerializer();
    }

}
