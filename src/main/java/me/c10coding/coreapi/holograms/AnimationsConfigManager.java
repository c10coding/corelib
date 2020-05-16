package me.c10coding.coreapi.holograms;

import me.c10coding.coreapi.files.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class AnimationsConfigManager extends ConfigManager {

    protected AnimationsConfigManager(JavaPlugin plugin) {
        super(plugin, "animations.yml");
    }

    protected boolean hasAnimation(String hologramName, int numLine){
        return config.getString("Animations." + hologramName + "." + numLine + ".Lines") != null;
    }

    protected boolean animationIsEnabled(String hologramName, int numLine){
        return config.getBoolean("Animations." + hologramName + "." + numLine + ".Enabled");
    }

    protected void setAnimationStatus(String hologramName, int numLine, boolean isEnabled){
        config.set("Animations." + hologramName + "." + numLine + ".Enabled", isEnabled);
    }

    protected List<String> getAnimationLines(String hologramName, int numLine){
        return config.getStringList("Animations." + hologramName + "." + numLine + ".Lines");
    }

    protected void removeAnimation(String hologramName){
        config.set("Animations." + hologramName, null);
    }

    protected void addAsAnimatable(String hologramName, int numLine){
        HologramsConfigManager hcm = new HologramsConfigManager(plugin);
        String hologramLine = hcm.getHologramLine(hologramName, numLine);
        List<String> lines = Arrays.asList(hologramLine.split(" "));
        config.set("Animations." + hologramName + "." + numLine + ".Lines", lines);
    }

}
