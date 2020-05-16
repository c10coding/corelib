package me.c10coding.coreapi.holograms;

import me.c10coding.coreapi.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class HologramHelper {

    private HologramsConfigManager hc;
    private Chat chatFactory = new Chat();

    public HologramHelper(JavaPlugin plugin){
        hc = new HologramsConfigManager(plugin);
    }

    public void createHologram(Player p, String hologramText, String hologramName){
        ArmorStand hologram = (ArmorStand) p.getWorld().spawnEntity(p.getLocation().subtract(0,1, 0), EntityType.ARMOR_STAND);
        setArmorStandTraits(hologram, hologramText);
        hc.createNewHologram(hologramName, hologramText, p.getLocation());
        hc.saveConfig();
    }

    public void removeHologram(String hologramName){
        List<ArmorStand> armorStands = new ArrayList<>();
        for(int x = 1; x <= hc.getNumLines(hologramName); x++){
            armorStands.add(hc.getHologramArmorStand(hologramName, x));
        }
        armorStands.stream().forEach(armorStand -> armorStand.remove());
        hc.removeHologram(hologramName);
        hc.saveConfig();
    }

    public void addLine(String hologramName, String hologramText){
        ArmorStand lastArmorStand = hc.getHologramLastLine(hologramName);
        Location nextArmorStandLoc = lastArmorStand.getLocation().subtract(0, hc.LINE_BUFFER, 0);
        ArmorStand nextArmorStand = (ArmorStand) nextArmorStandLoc.getWorld().spawnEntity(nextArmorStandLoc, EntityType.ARMOR_STAND);
        setArmorStandTraits(nextArmorStand, hologramText);
        hc.addLine(hologramName, hologramText, nextArmorStand);
        hc.saveConfig();
    }

    public void editLine(String hologramName, String newHologramText, int numLine){
        ArmorStand armorStand = hc.getHologramArmorStand(hologramName, numLine);
        armorStand.setCustomName(chatFactory.chat(newHologramText));
        hc.editLine(hologramName, newHologramText, numLine);
        hc.saveConfig();
    }

    public List<String> getAllNames(){
        List<String> names = new ArrayList<>();
        hc.getAllHologramNames().stream().forEach(name -> names.add(name));
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
        hologram.setCustomName(chatFactory.chat(hologramText));
        hologram.setGravity(false);
    }



}
