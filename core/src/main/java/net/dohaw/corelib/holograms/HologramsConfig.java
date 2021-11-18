package net.dohaw.corelib.holograms;

import net.dohaw.corelib.YamlFile;
import net.dohaw.corelib.serializers.LocationSerializer;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class HologramsConfig extends YamlFile {

    private LocationSerializer ls;
    protected final double LINE_BUFFER = 0.3;

    protected HologramsConfig(JavaPlugin plugin) {
        super(plugin, "holograms/holograms.yml");
        this.ls = new LocationSerializer();
    }

    protected void createNewHologram(String name, String hologramText, Location loc, UUID entityUUID){
        loadedFile.set(getPath("Text", name, 1), hologramText);
        loadedFile.set(getPath("Location", name, 1), ls.toString(loc));
        loadedFile.set(getPath("UUID", name, 1), entityUUID.toString());
    }

    protected void addLine(String hologramName, String hologramText, ArmorStand as){
        int numLine = getNumLines(hologramName) + 1;
        loadedFile.set(getPath("Text", hologramName, numLine), hologramText);
        loadedFile.set(getPath("Location", hologramName, numLine), ls.toString(as.getLocation()));
        loadedFile.set(getPath("UUID", hologramName, numLine), as.getUniqueId().toString());
    }

    protected void editLine(String hologramName, String hologramText, int numLine){
        loadedFile.set(getPath("Text", hologramName, numLine), hologramText);
    }

    protected void removeHologram(String hologramName){
        loadedFile.set("Holograms." + hologramName, null);
    }

    protected boolean isAHologram(String nameOfHologram){
        return loadedFile.getString(getPath("Text", nameOfHologram, 1)) != null;
    }

    /*
    protected Location getHologramLocation(String hologramName){
        return ls.toLocationFromPath(getPath("Location", hologramName, 1));
    }*/

    protected int getNumLines(String hologramName){
        int numLine = 1;
        while(loadedFile.getString(getPath("Text", hologramName, numLine)) != null){
            numLine++;
        }
        return (numLine - 1);
    }

    public UUID getHologramUUID(String hologramName, int numLine){
        return UUID.fromString(loadedFile.getString(getPath("UUID", hologramName, numLine)));
    }

    protected ArmorStand getHologramArmorStand(String hologramName, int numLine){
        Location armorStandLocation = ls.toLocation(getPath("Location", hologramName, numLine));
        if(!armorStandLocation.getChunk().isLoaded()){
            armorStandLocation.getChunk().load();
        }
        Collection<Entity> nearbyEntities = armorStandLocation.getWorld().getNearbyEntities(armorStandLocation, 1.5, 1.5, 1.5);
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
        return loadedFile.getString(getPath("Text", hologramName, numLine));
    }

    protected ArmorStand getHologramLastLine(String hologramName){
        return getHologramArmorStand(hologramName, getNumLines(hologramName));
    }

    protected Set<String> getAllHologramNames(){
        ConfigurationSection cs = loadedFile.getConfigurationSection("Holograms");
        if(cs != null){
            return cs.getKeys(false);
        }
        return null;
    }

    private String getPath(String key, String hologramName, int numLine){
        return "Holograms." + hologramName + "." + numLine + "." + key;
    }

}
