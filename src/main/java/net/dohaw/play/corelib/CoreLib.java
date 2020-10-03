//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.dohaw.play.corelib;

import net.dohaw.play.corelib.serializers.LocationSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CoreLib {

    private static JavaPlugin instance;

    public static JavaPlugin getInstance() {
        return instance;
    }

    public static void setInstance(JavaPlugin instance) {
        CoreLib.instance = instance;
    }

    public LocationSerializer createLocationSerializer(FileConfiguration config){
        return new LocationSerializer(config);
    }

}
