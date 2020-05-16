package me.c10coding.coreapi.holograms;

import me.c10coding.coreapi.chat.Chat;
import me.c10coding.coreapi.files.ConfigManager;
import me.c10coding.coreapi.serializers.LocationSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class HologramsConfigManager extends ConfigManager {

    private LocationSerializer ls;
    protected final double LINE_BUFFER = 0.3;

    protected HologramsConfigManager(JavaPlugin plugin) {
        super(plugin, "holograms.yml");
        this.ls = new LocationSerializer(config);
    }

    protected void createNewHologram(String name, String hologramText, Location loc){
        config.set(getPath("Text", name, 1), hologramText);
        config.set(getPath("Location", name, 1), ls.toString(loc));
    }

    protected void addLine(String hologramName, String hologramText, ArmorStand as){
        int numLine = getNumLines(hologramName) + 1;
        config.set(getPath("Text", hologramName, numLine), hologramText);
        config.set(getPath("Location", hologramName, numLine), ls.toString(as.getLocation()));
    }

    protected void editLine(String hologramName, String hologramText, int numLine){
        config.set(getPath("Text", hologramName, numLine), hologramText);
    }

    protected void removeHologram(String hologramName){
        config.set("Holograms." + hologramName, null);
    }

    protected boolean isAHologram(String nameOfHologram){
        return config.getString(getPath("Text", nameOfHologram, 1)) != null;
    }

    /*
    protected Location getHologramLocation(String hologramName){
        return ls.toLocationFromPath(getPath("Location", hologramName, 1));
    }*/

    protected int getNumLines(String hologramName){
        int numLine = 1;
        while(config.getString(getPath("Text", hologramName, numLine)) != null){
            numLine++;
        }
        return (numLine - 1);
    }

    protected ArmorStand getHologramArmorStand(String hologramName, int numLine){
        Chat chatFactory = new Chat();
        Location armorStandLocation = ls.toLocationFromPath(getPath("Location", hologramName, numLine));
        Collection<Entity> nearbyEntities = armorStandLocation.getWorld().getNearbyEntities(armorStandLocation, 5, 5, 5);
        for(Entity e : nearbyEntities){
            if(e instanceof ArmorStand){
                String currentNumLine = chatFactory.removeChatColor(chatFactory.chat(getHologramLine(hologramName, numLine)));
                String firstHologramLine = chatFactory.removeChatColor(e.getCustomName());
                if(currentNumLine.equals(firstHologramLine)){
                    return (ArmorStand) e;
                }
            }
        }
        return null;
    }

    protected String getHologramLine(String hologramName, int numLine){
        return config.getString(getPath("Text", hologramName, numLine));
    }

    protected ArmorStand getHologramLastLine(String hologramName){
        return getHologramArmorStand(hologramName, getNumLines(hologramName));
    }

    protected Set<String> getAllHologramNames(){
        ConfigurationSection cs = config.getConfigurationSection("Holograms");
        return cs.getKeys(false);
    }

    private String getPath(String key, String hologramName, int numLine){
        return "Holograms." + hologramName + "." + numLine + "." + key;
    }



}
