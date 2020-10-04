package net.dohaw.corelib.serializers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class LocationSerializer {

    private FileConfiguration config;

    public LocationSerializer(FileConfiguration config) {
        this.config = config;
    }

    public LocationSerializer(){}

    public void storeLocation(String path, Location loc) {
        config.set(path, "World: " + loc.getWorld().getName() + ";X: " + loc.getBlockX() + ";Y: " + loc.getBlockY() + ";Z: " + loc.getBlockZ());
    }

    public String toString(Location loc){
        return "World: " + loc.getWorld().getName() + ";X: " + loc.getBlockX() + ";Y: " + loc.getBlockY() + ";Z: " + loc.getBlockZ();
    }

    public Location toLocationFromPath(String path) {

        World w;
        double x,y,z;

        String line = config.getString(path);

        String[] arrLine = line.split(";");

        w = Bukkit.getWorld(arrLine[0].substring(7));
        x = Double.parseDouble(arrLine[1].substring(3));
        y = Double.parseDouble(arrLine[2].substring(3));
        z = Double.parseDouble(arrLine[3].substring(3));

        return new Location(w, x, y, z);
    }

    public Location toLocationFromLine(String line) {

        World w;
        double x,y,z;

        String[] arrLine = line.split(";");

        w = Bukkit.getWorld(arrLine[0].substring(7));
        x = Double.parseDouble(arrLine[1].substring(3));
        y = Double.parseDouble(arrLine[2].substring(3));
        z = Double.parseDouble(arrLine[3].substring(3));

        return new Location(w, x, y, z);
    }

}
