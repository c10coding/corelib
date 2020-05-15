package me.c10coding.coreapi.holograms;

import me.c10coding.coreapi.chat.Chat;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HologramHelper {

    private JavaPlugin plugin;
    private HologramsConfigManager hc;
    private Chat chatFactory = new Chat();

    public HologramHelper(JavaPlugin plugin){
        this.plugin = plugin;
        hc = new HologramsConfigManager(plugin);
    }

    public void createHologram(Player p, String hologramText, String hologramName){
        ArmorStand hologram = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
        hologram.setVisible(false);
        hologram.setCustomNameVisible(true);
        hologram.setCustomName(hologramText);
        hologram.setGravity(false);
        hc.createNewHologram(hologramName, hologramText, p.getLocation());
        hc.saveConfig();
    }



}
