package me.c10coding.coreapi.numbers;

import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class MathHelper {

    public static double roundToPoint5(double x) {
        return Math.round(x * 2) / 2.0;
    }

    public static long secondsToTicks(double seconds) {
        return (long) (seconds*20);
    }

    public static double roundToPoint1(double x) {
        DecimalFormat df = new DecimalFormat("0.#");
        return Double.parseDouble(df.format(x));
    }

    public static String getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (0 <= rotation && rotation < 22.5) {
            return "N";
        } else if (22.5 <= rotation && rotation < 67.5) {
            return "NE";
        } else if (67.5 <= rotation && rotation < 112.5) {
            return "E";
        } else if (112.5 <= rotation && rotation < 157.5) {
            return "SE";
        } else if (157.5 <= rotation && rotation < 202.5) {
            return "S";
        } else if (202.5 <= rotation && rotation < 247.5) {
            return "SW";
        } else if (247.5 <= rotation && rotation < 292.5) {
            return "W";
        } else if (292.5 <= rotation && rotation < 337.5) {
            return "NW";
        } else if (337.5 <= rotation && rotation < 360.0) {
            return "N";
        } else {
            return null;
        }
    }
}
