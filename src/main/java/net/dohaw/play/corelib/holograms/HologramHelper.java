package net.dohaw.play.corelib.holograms;

import net.dohaw.play.corelib.StringUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HologramHelper {

    private JavaPlugin plugin;
    private HologramsConfig hc;
    private AnimationsConfig acm;
    private StringUtils stringUtils = new StringUtils();

    public HologramHelper(JavaPlugin plugin){
        this.plugin = plugin;
        hc = new HologramsConfig(plugin);
        acm = new AnimationsConfig(plugin);
    }

    public void startAnimations(){
        for(String name : getAllNames()){
            int numLines = hc.getNumLines(name);
            for(int x = 1; x <= numLines; x++){
                if(acm.animationIsEnabled(name, x)){
                    new HologramAnimator(plugin, name, x).runTaskTimer(plugin, 0L, 20L);
                }else{
                    /*
                    If the animation is disabled, but it's in the animations config file, set the line back to what it originally is without the animations.
                     */
                    if(hasAnimation(name, x)){
                        ArmorStand as = hc.getHologramArmorStand(name, x);
                        String line = hc.getHologramLine(name, x);
                        as.setCustomName(line);
                    }
                }
            }
        }
    }

    public void setAsAnimatable(String hologramName, int numLine){
        acm.addAsAnimatable(hologramName, numLine);
        acm.saveConfig();
    }

    public boolean isBeingAnimated(String hologramName, int numLine){
        return acm.animationIsEnabled(hologramName, numLine);
    }

    public boolean hasAnimation(String hologramName, int numLine){
        return acm.hasAnimation(hologramName, numLine);
    }

    public void setAnimationStatus(String hologramName, int numLine, boolean isEnabled){
        acm.setAnimationStatus(hologramName, numLine, isEnabled);
        acm.saveConfig();
    }

    public void createHologram(Location loc, String hologramText, String hologramName){
        loadChunk(loc);
        ArmorStand hologram = (ArmorStand) loc.getWorld().spawnEntity(loc.subtract(0,1, 0), EntityType.ARMOR_STAND);
        setArmorStandTraits(hologram, hologramText);
        hc.createNewHologram(hologramName, hologramText, loc, hologram.getUniqueId());
        hc.saveConfig();
    }

    public void removeHologram(String hologramName){
        for(int x = 1; x <= hc.getNumLines(hologramName); x++){
            ArmorStand as = hc.getHologramArmorStand(hologramName, x);
            if(as != null){
                if(!as.getLocation().getChunk().isLoaded()){
                    as.getLocation().getChunk().load();
                }
                as.remove();
            }
        }
        hc.removeHologram(hologramName);
        acm.removeAnimation(hologramName);
        acm.saveConfig();
        hc.saveConfig();
    }

    public void removeAllHolograms(){
        List<ArmorStand> armorstands = hc.getAllArmorStands();
        World w = armorstands.get(0).getLocation().getWorld();
        for(ArmorStand as : armorstands){
            if(w.getEntities().contains(as)){
                as.remove();
            }
        }
    }

    public void addLine(String hologramName, String hologramText){
        ArmorStand lastArmorStand = hc.getHologramLastLine(hologramName);
        Location nextArmorStandLoc = lastArmorStand.getLocation().subtract(0, hc.LINE_BUFFER, 0);
        ArmorStand nextArmorStand = (ArmorStand) nextArmorStandLoc.getWorld().spawnEntity(nextArmorStandLoc, EntityType.ARMOR_STAND);
        setArmorStandTraits(nextArmorStand, hologramText);
        hc.addLine(hologramName, hologramText, nextArmorStand);
        hc.saveConfig();
    }

    public void addAnimationLine(String hologramName, String hologramText, int numLine){
        acm.addAnimationLine(hologramName, hologramText, numLine);
        acm.saveConfig();
    }

    public void setAnimationLines(String hologramName, List<String> lines, int numLine){
        acm.setAnimationLines(hologramName, lines, numLine);
        acm.saveConfig();
    }

    public void editLine(String hologramName, String newHologramText, int numLine){
        ArmorStand armorStand = hc.getHologramArmorStand(hologramName, numLine);
        armorStand.setCustomName(stringUtils.colorString(newHologramText));
        hc.editLine(hologramName, newHologramText, numLine);
        hc.saveConfig();
    }

    public String getLine(String hologramName, int numLine){
        return hc.getHologramLine(hologramName, numLine);
    }

    public List<String> getAllNames(){
        List<String> names = new ArrayList<>();
        Set<String> hologramNames = hc.getAllHologramNames();
        if(hologramNames != null){
            for(String s : hologramNames){
                names.add(s);
            }
        }
        return names;
    }

    public List<Location> getAllLocations(){
        List<Location> locations = new ArrayList<>();
        for(int x = 0; x < getAllNames().size(); x++){
            String name = getAllNames().get(x);
            locations.add(hc.getHologramArmorStand(name, 1).getLocation());
        }
        return locations;
    }

    public boolean isValidNumLine(String hologramName, int numLine){
        return hc.getNumLines(hologramName) >= numLine;
    }

    public boolean isAHologram(String name){
        return hc.isAHologram(name);
    }

    public void setArmorStandTraits(ArmorStand hologram, String hologramText){
        hologram.setVisible(false);
        hologram.setCustomNameVisible(true);
        hologram.setCustomName(stringUtils.colorString(hologramText));
        hologram.setGravity(false);
    }

    private void loadChunk(Location loc){
        if(loc.getChunk().isLoaded()){
            loc.getChunk().load();
        }
    }




}
