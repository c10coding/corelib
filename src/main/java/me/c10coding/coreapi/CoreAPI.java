//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.c10coding.coreapi;

import me.c10coding.coreapi.chat.ChatFactory;
import me.c10coding.coreapi.helpers.*;
import me.c10coding.coreapi.holograms.HologramHelper;
import me.c10coding.coreapi.serializers.ItemStackSerializer;
import me.c10coding.coreapi.serializers.LocationSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CoreAPI extends JavaPlugin{

    private static CoreAPI instance;
    private ItemStackHelper itemStackHelper;
    private ChatFactory chatFactory;
    private MathHelper mathHelper;
    private PlayerHelper playerHelper;
    private ItemStackSerializer itemStackSerializer;
    private ProbabilityUtilities probabilityUtilities;
    private BlockHelper blockHelper;
    private EnumHelper enumHelper;
    private CustomInvHelper customInvHelper;

    @Override
    public void onEnable(){
        instance = this;
        this.itemStackHelper = new ItemStackHelper();
        this.chatFactory = new ChatFactory();
        this.mathHelper = new MathHelper();
        this.playerHelper = new PlayerHelper();
        this.itemStackSerializer = new ItemStackSerializer();
        this.probabilityUtilities = new ProbabilityUtilities();
        this.blockHelper = new BlockHelper();
        this.enumHelper = new EnumHelper();
        this.customInvHelper = new CustomInvHelper();
    }

    @Override
    public void onDisable(){}

    public LocationSerializer createLocationSerializer(FileConfiguration config){
        return new LocationSerializer(config);
    }

    public ChatFactory getChatFactory(){
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

    public ProbabilityUtilities getProbabilityUtilities(){
        return probabilityUtilities;
    }

    public ItemStackSerializer getItemStackSerializer(){
        return itemStackSerializer;
    }

    public ItemStackHelper getItemStackHelper(){
        return itemStackHelper;
    }

    public BlockHelper getBlockHelper(){
        return blockHelper;
    }

    public EnumHelper getEnumHelper(){
        return enumHelper;
    }

    public static CoreAPI getInstance(){
        return instance;
    }

    public CustomInvHelper getCustomInvHelper() {
        return customInvHelper;
    }
}
