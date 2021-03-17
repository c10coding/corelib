package net.dohaw.corelib.holograms;

import net.dohaw.corelib.CoreLib;
import net.dohaw.corelib.JPUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HologramAPI {

    public static JavaPlugin hookingPlugin = CoreLib.getInstance();
    private static HologramHelper hologramHelper;

    public static void init() throws IOException {

        Map<String, Object> folders = new HashMap<>();
        folders.put("holograms", hookingPlugin.getDataFolder());
        JPUtils.validateFilesOrFolders(folders, true);

        File hologramsFile = new File(hookingPlugin.getDataFolder() + "/holograms", "holograms.yml");
        File animationsFile = new File(hookingPlugin.getDataFolder() + "holograms", "animations.yml");

        if(!hologramsFile.exists()){
            hologramsFile.createNewFile();
        }

        if(!animationsFile.createNewFile()){
            animationsFile.createNewFile();
        }

        hologramHelper = new HologramHelper(hookingPlugin);

    }

    public static HologramHelper getHologramHelper(){
        return hologramHelper;
    }

}
