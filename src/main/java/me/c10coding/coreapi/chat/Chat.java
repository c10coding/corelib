//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.c10coding.coreapi.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {
    public Chat() {
    }

    public static String firstUpperRestLower(String s) {
        return s.substring(0, 1) + s.substring(1);
    }

    public static String valueOf(int x) {
        return String.valueOf(x);
    }

    public static String valueOf(double x) {
        return String.valueOf(x);
    }

    public static String valueOf(boolean x) {
        return String.valueOf(x);
    }

    public static String formatString(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void sendPlayerMessage(String s, boolean wantPrefix, Player p, String prefix) {
        if (wantPrefix) {
            p.sendMessage(formatString(prefix + " &r" + s));
        } else {
            p.sendMessage(formatString("&r" + s));
        }

    }

    public void sendConsoleMessage(String s, String prefix) {
        Bukkit.getConsoleSender().sendMessage(formatString(prefix + " &r" + s));
    }

    public static void bsm(String s) {
        Bukkit.broadcastMessage(s);
    }
}
