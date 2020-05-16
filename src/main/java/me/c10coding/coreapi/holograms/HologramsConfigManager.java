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

import java.util.*;

public class HologramsConfigManager extends ConfigManager {

    private LocationSerializer ls;
    protected final double LINE_BUFFER = 0.3;

    protected HologramsConfigManager(JavaPlugin plugin) {
        super(plugin, "holograms.yml");
        this.ls = new LocationSerializer(config);
    }

    protected void createNewHologram(String name, String hologramText, Location loc, UUID entityUUID){
        config.set(getPath("Text", name, 1), hologramText);
        config.set(getPath("Location", name, 1), ls.toString(loc));
        config.set(getPath("UUID", name, 1), entityUUID.toString());
    }

    protected void addLine(String hologramName, String hologramText, ArmorStand as){
        int numLine = getNumLines(hologramName) + 1;
        config.set(getPath("Text", hologramName, numLine), hologramText);
        config.set(getPath("Location", hologramName, numLine), ls.toString(as.getLocation()));
        config.set(getPath("UUID", hologramName, numLine), as.getUniqueId().toString());
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

    public UUID getHologramUUID(String hologramName, int numLine){
        return UUID.fromString(config.getString(getPath("UUID", hologramName, numLine)));
    }

    protected ArmorStand getHologramArmorStand(String hologramName, int numLine){
        Chat chatFactory = new Chat();
        Location armorStandLocation = ls.toLocationFromPath(getPath("Location", hologramName, numLine));
        Collection<Entity> nearbyEntities = armorStandLocation.getWorld().getNearbyEntities(armorStandLocation, 5, 5, 5);
        for(Entity e : nearbyEntities){
            if(e instanceof ArmorStand){
                UUID configLineUUID = getHologramUUID(hologramName, numLine);
                UUID entityUUID = e.getUniqueId();
                if(configLineUUID.equals(entityUUID)){
                    return (ArmorStand) e;
                }
            }
        }
        return null;
    }

    protected List<ArmorStand> getAllArmorStands(){
        List<ArmorStand> armorStands = new ArrayList<>();
        for(String s : getAllHologramNames()){
            int numLines = getNumLines(s);
            for(int x = 1; x <= numLines; x++){
                armorStands.add(getHologramArmorStand(s, x));
            }
        }
        return armorStands;
    }

    protected List<String> getAllHologramLines(String hologramName){
        List<String> lines = new ArrayList<>();
        for(int x = 1; x <= getNumLines(hologramName); x++){
            lines.add(getHologramLine(hologramName,x));
        }
        return lines;
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
