package net.dohaw.corelib.helpers;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;
import java.util.stream.Collectors;

public abstract class ItemStackHelper {

    static InternalsProvider internals;

    static{
        String internalsName = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try{
            String packageName = ItemStackHelper.class.getPackage().getName();
            internals = (InternalsProvider) Class.forName(packageName + "." + internalsName).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            Bukkit.getLogger().log(Level.SEVERE, "ItemStackHelper could not find a valid implementation for this server version: " + internalsName);
            internals = new InternalsProvider();
        }
    }

    public static boolean isArmor(ItemStack itemStack) {
        return isArmor(itemStack.getType());
    }

    public static boolean isArmor(Material mat) {
        if (mat == null)
            return false;
        String typeNameString = mat.name();
        return typeNameString.endsWith("_HELMET")
                || typeNameString.endsWith("_CHESTPLATE")
                || typeNameString.endsWith("_LEGGINGS")
                || typeNameString.endsWith("_BOOTS");
    }

    public static boolean isToolOrWeapon(final ItemStack itemStack){
        if(itemStack == null){
            return false;
        }else{
            String typeName = itemStack.getType().name().toLowerCase();
            return typeName.contains("pickaxe") || typeName.contains("axe") || typeName.contains("sword") || typeName.contains("shovel") || typeName.contains("hoe") || itemStack.getType().equals(Material.FLINT_AND_STEEL) || itemStack.getType().equals(Material.FISHING_ROD) || itemStack.getType().name().toLowerCase().equalsIgnoreCase("trident") || itemStack.getType().name().toLowerCase().equalsIgnoreCase("bow");
        }
    }

    public static void addGlowToItem(ItemStack stack){
        ItemMeta meta = stack.getItemMeta();
        meta.addEnchant(Enchantment.WATER_WORKER, 70, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        stack.setItemMeta(meta);
    }

    public static ChatColor matchArmorColorWithChatColor(Color armorColor){
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
    public static ItemStack getPlayerHead(UUID uuid){

        boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");

        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        ItemStack item = new ItemStack(type, 1);

        if (!isNewVersion)
            item.setDurability((short) 3);

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(Bukkit.getOfflinePlayer(uuid).getName());

        item.setItemMeta(meta);

        return item;

    }

    public static ItemStack getBase64PlayerHead(String base64){
        return internals.getBase64Head(base64);
    }

    public static boolean hasEnchantment(ItemStack stack, Enchantment ench){
        Validate.notNull(stack, "The stack can't be null!");
        ItemMeta meta = stack.getItemMeta();
        return meta instanceof EnchantmentStorageMeta ? ((EnchantmentStorageMeta)meta).hasStoredEnchant(ench) : meta.hasEnchant(ench);
    }

}
