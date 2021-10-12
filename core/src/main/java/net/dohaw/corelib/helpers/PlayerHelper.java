package net.dohaw.corelib.helpers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerHelper {

    /*
        Doesn't compare Y
     */
    public static boolean isPlayerWithinRegion(Player p, double minX, double maxX, double minZ, double maxZ){

        Location playerLoc = p.getLocation();
        int x = playerLoc.getBlockX();
        int z = playerLoc.getBlockZ();

        if(x >= minX && x <= -maxX){
            return z >= -minZ && z <= -maxZ;
        }
        return false;

    }

    /*
        Compares Y
     */
    public static boolean isPlayerWithinRegion(Player p, double minX, double maxX, double minY, double maxY, double minZ, double maxZ){

        Location playerLoc = p.getLocation();
        int x = playerLoc.getBlockX();
        int y = playerLoc.getBlockY();
        int z = playerLoc.getBlockZ();

        if(x >= -minX && x <= maxX){
            if(y >= minY && y <= maxY){
                return z >= minZ && z <= maxZ;
            }
        }
        return false;

    }

}
