//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.dohaw.corelib;

import net.dohaw.corelib.serializers.LocationSerializer;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CoreLib {

    private static JavaPlugin instance;

    public static JavaPlugin getInstance() {
        return instance;
    }

    public static void setInstance(JavaPlugin plugin) {
        instance = plugin;
    }

    public LocationSerializer createLocationSerializer(FileConfiguration config){
        return new LocationSerializer(config);
    }

}
