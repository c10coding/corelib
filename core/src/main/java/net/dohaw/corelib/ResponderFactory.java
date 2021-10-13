package net.dohaw.corelib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResponderFactory {

    private CommandSender messageGetter;
    private String prefix;

    public ResponderFactory(CommandSender messageGetter, String prefix){
        this.messageGetter = messageGetter;
        this.prefix = prefix;
    }

    public ResponderFactory(CommandSender messageGetter){
        this.messageGetter = messageGetter;
        this.prefix = null;
    }

    public void sendCenteredMessage( String message){
        final  int CENTER_PX = 154;
        if(message == null || message.equals("")) messageGetter.sendMessage("");
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
        messageGetter.sendMessage(sb.toString() + message);
    }

    public void sendMessage(String s) {
        if (prefix != null) {
            messageGetter.sendMessage(StringUtils.colorString(prefix + " &r" + s));
        } else {
            messageGetter.sendMessage(StringUtils.colorString("&r" + s));
        }
    }

    public static void bsm(String s) {
        Bukkit.broadcastMessage(s);
    }

}
