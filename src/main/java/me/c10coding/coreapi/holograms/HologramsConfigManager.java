package me.c10coding.coreapi.holograms;

import me.c10coding.coreapi.files.ConfigManager;
import me.c10coding.coreapi.serializers.LocationSerializer;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HologramsConfigManager extends ConfigManager {

    private LocationSerializer ls;
    private final double LINE_BUFFER = 0.3;

    protected HologramsConfigManager(JavaPlugin plugin) {
        super(plugin, "holograms.yml");
        this.ls = new LocationSerializer(config);
    }

    protected void createNewHologram(String name, String hologramText, Location loc){
        config.set(getPath("Text", name, 1), hologramText);
        config.set(getPath("Location", name, 1), ls.toString(loc));
    }

    protected void addLine(String hologramName, String hologramText){
        int numLine = getNumLines(hologramName);
        Location lineBeforeLocation = ls.toLocationFromLine(config.getString(getPath("Location", hologramName, (numLine - 1))));
        config.set(getPath("Text", hologramName, numLine), hologramText);
        config.set(getPath("Location", hologramName, numLine), ls.toString(lineBeforeLocation.subtract(0, LINE_BUFFER, 0)));
    }

    protected void editLine(String hologramText, String hologramName, int numLine){
        config.set(getPath("Text", hologramName, numLine), hologramText);
    }

    protected void removeHologram(String hologramName){
        config.set("Holograms." + hologramName, null);
    }

    protected boolean isHologram(String nameOfHologram){
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
        Location armorStandLocation = ls.toLocationFromLine(getPath("Location", hologramName, numLine));
        Collection<Entity> nearbyEntities = armorStandLocation.getWorld().getNearbyEntities(armorStandLocation, 0, 0.5, 0);
        List<Entity> armorStands = new ArrayList<>();
        for(Entity e : nearbyEntities){
            if(e instanceof ArmorStand){
                if(e.getLocation().equals(armorStandLocation)){
                    return (ArmorStand) e;
                }
            }
        }
        return null;
    }

    private String getPath(String key, String hologramName, int numLine){
        return "Holograms." + hologramName + "." + numLine + "." + key;
    }



}
