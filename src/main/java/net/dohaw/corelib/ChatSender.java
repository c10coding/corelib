package net.dohaw.corelib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatSender {

    public static void sendCenteredMessage(Player player, String message){
        final  int CENTER_PX = 154;
        if(message == null || message.equals("")) player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
                continue;
            }else if(previousCode){
                previousCode = false;
                if(c == 'l' || c == 'L'){
                    isBold = true;
                    continue;
                }else isBold = false;
            }else{
                StringUtils.DefaultFontInfo dFI = StringUtils.DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = StringUtils.DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb.toString() + message);
    }

    public static void sendPlayerMessage(String s, boolean wantPrefix, Player p, String prefix) {
        if (wantPrefix) {
            p.sendMessage(StringUtils.colorString(prefix + " &r" + s));
        } else {
            p.sendMessage(StringUtils.colorString("&r" + s));
        }
    }

    public static void sendPlayerMessage(String s, boolean wantPrefix, CommandSender sender, String prefix) {
        if (wantPrefix) {
            sender.sendMessage(StringUtils.colorString(prefix + " &r" + s));
        } else {
            sender.sendMessage(StringUtils.colorString("&r" + s));
        }
    }

    public static void sendConsoleMessage(String s, String prefix) {
        Bukkit.getConsoleSender().sendMessage(StringUtils.colorString(prefix + " &r" + s));
    }

    public static void bsm(String s) {
        Bukkit.broadcastMessage(s);
    }

}
