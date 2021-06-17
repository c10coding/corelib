package net.dohaw.corelib.serializers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationSerializer {

    public LocationSerializer(){}

    public String toString(Location location) {
        return "World: " + location.getWorld().getName() + ";X: " + location.getX() + ";Y: " + location.getY() + ";Z: " + location.getZ() + ";Yaw: " + location.getYaw() + ";Pitch: " + location.getPitch();
    }

    public Location toLocation(String strLocation) {

        World world;
        double x,y,z;
        float yaw, pitch;

        String[] arrStrLocation = strLocation.split(";");

        world = Bukkit.getWorld(arrStrLocation[0].substring(7));
        x = Double.parseDouble(arrStrLocation[1].substring(3));
        y = Double.parseDouble(arrStrLocation[2].substring(3));
        z = Double.parseDouble(arrStrLocation[3].substring(3));
        yaw = Float.parseFloat(arrStrLocation[4].replace("Yaw:",""));
        pitch = Float.parseFloat(arrStrLocation[5].replace("Pitch:",""));

        return new Location(world, x, y, z, yaw, pitch);
    }

}
