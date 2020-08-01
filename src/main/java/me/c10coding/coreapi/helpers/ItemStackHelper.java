package me.c10coding.coreapi.helpers;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemStackHelper {

    public boolean isArmor(ItemStack itemStack) {
        if (itemStack == null)
            return false;
        String typeNameString = itemStack.getType().name();
        return typeNameString.endsWith("_HELMET")
                || typeNameString.endsWith("_CHESTPLATE")
                || typeNameString.endsWith("_LEGGINGS")
                || typeNameString.endsWith("_BOOTS");
    }

    public boolean isToolOrWeapon(final ItemStack itemStack){
        if(itemStack == null){
            return false;
        }else{
            String typeName = itemStack.getType().name().toLowerCase();
            return typeName.contains("pickaxe") || typeName.contains("axe") || typeName.contains("sword") || typeName.contains("shovel") || typeName.contains("hoe") || itemStack.getType().equals(Material.FLINT_AND_STEEL) || itemStack.getType().equals(Material.FISHING_ROD) || itemStack.getType().name().toLowerCase().equalsIgnoreCase("trident") || itemStack.getType().name().toLowerCase().equalsIgnoreCase("bow");
        }
    }

    public ItemStack addGlowToItem(ItemStack stack){
        ItemMeta meta = stack.getItemMeta();
        meta.addEnchant(Enchantment.WATER_WORKER, 70, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        stack.setItemMeta(meta);
        return stack;
    }

    public ChatColor matchArmorColorWithChatColor(Color armorColor){
        if(armorColor.equals(Color.GRAY)){
            return ChatColor.GRAY;
        }else if(armorColor.equals(Color.RED)){
            return ChatColor.RED;
        }else if(armorColor.equals(Color.fromBGR(255, 192, 250))){
            return ChatColor.LIGHT_PURPLE;
        }else if(armorColor.equals(Color.YELLOW)){
            return ChatColor.YELLOW;
        }else if(armorColor.equals(Color.BLUE)){
            return ChatColor.BLUE;
        }else if(armorColor.equals(Color.LIME)){
            return ChatColor.GREEN;
        }else if(armorColor.equals(Color.fromBGR(0,100,0))){
            return ChatColor.DARK_GREEN;
        }else if(armorColor.equals(Color.fromBGR(125, 0, 0))){
            return ChatColor.DARK_RED;
        }else if(armorColor.equals(Color.BLACK)){
            return ChatColor.BLACK;
        }else if(armorColor.equals(Color.fromBGR(197,179,88))){
            return ChatColor.GOLD;
        }else if(armorColor.equals(Color.WHITE)) {
            return ChatColor.WHITE;
        }else if(armorColor.equals(Color.PURPLE)){
            return ChatColor.LIGHT_PURPLE;
        }else{
            return ChatColor.DARK_AQUA;
        }
    }

    /*
        No lore no nothing. Just the head
     */
    public ItemStack getHead(Player player, Material skullMat) {

        ItemStack item = new ItemStack(skullMat, 1 , (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(player.getName());
        item.setItemMeta(meta);

        return item;

    }

    public static String getRomanNumeral(int level){
        String romanNumeral = "";
        if(level == 1){
            romanNumeral = "I";
        }else if(level == 2){
            romanNumeral = "II";
        }else if(level == 3){
            romanNumeral = "III";
        }else if(level == 4){
            romanNumeral = "IV";
        }else if(level == 5){
            romanNumeral = "V";
        }else if(level == 6){
            romanNumeral = "VI";
        }else if(level == 7){
            romanNumeral = "VII";
        }else if(level == 8){
            romanNumeral = "VIII";
        }else if(level == 9){
            romanNumeral = "IX";
        }else if(level == 10){
            romanNumeral = "X";
        }
        return romanNumeral;
    }

}
