package me.c10coding.coreapi.holograms;

import me.c10coding.coreapi.chat.Chat;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class HologramAnimator extends BukkitRunnable {

    private ArmorStand hologram;
    private List<String> animationLines;
    private HologramsConfigManager hcm;
    private AnimationsConfigManager acm;
    private Chat chatFactory = new Chat();
    String text = "";
    int counter = 0;

    public HologramAnimator(JavaPlugin plugin, String hologramName, int numLine){
        this.hcm = new HologramsConfigManager(plugin);
        this.acm = new AnimationsConfigManager(plugin);
        this.hologram = hcm.getHologramArmorStand(hologramName, numLine);
        this.animationLines = acm.getAnimationLines(hologramName, numLine);
    }

    @Override
    public void run() {

        if (counter == animationLines.size()) {
            counter = 0;
        }
        text = animationLines.get(counter);
        hologram.setCustomName(chatFactory.chat(text));
        counter++;

    }
}
