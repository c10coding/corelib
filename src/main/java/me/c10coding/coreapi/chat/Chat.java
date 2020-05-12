//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.c10coding.coreapi.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Chat {

    public String firstUpperRestLower(String s) {
        return s.substring(0, 1) + s.substring(1);
    }

    public String valueOf(int x) {
        return String.valueOf(x);
    }

    public String valueOf(double x) {
        return String.valueOf(x);
    }

    public String valueOf(boolean x) {
        return String.valueOf(x);
    }

    public String formatString(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public void sendPlayerMessage(String s, boolean wantPrefix, Player p, String prefix) {
        if (wantPrefix) {
            p.sendMessage(formatString(prefix + " &r" + s));
        } else {
            p.sendMessage(formatString("&r" + s));
        }
    }

    public void sendPlayerMessage(String s, boolean wantPrefix, CommandSender sender, String prefix) {
        if (wantPrefix) {
            sender.sendMessage(formatString(prefix + " &r" + s));
        } else {
            sender.sendMessage(formatString("&r" + s));
        }
    }

    public void sendConsoleMessage(String s, String prefix) {
        Bukkit.getConsoleSender().sendMessage(formatString(prefix + " &r" + s));
    }

    public void bsm(String s) {
        Bukkit.broadcastMessage(s);
    }

    public String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public String removeChatColor(String s){
        return ChatColor.stripColor(s);
    }
}
