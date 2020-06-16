package me.c10coding.coreapi.helpers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemStackHelper {

    public static boolean isArmor(ItemStack itemStack) {
        if (itemStack == null)
            return false;
        String typeNameString = itemStack.getType().name();
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
            return typeName.contains("pickaxe") || typeName.contains("axe") || typeName.contains("sword") || typeName.contains("shovel") || typeName.contains("hoe") || itemStack.getType().equals(Material.FLINT_AND_STEEL) || itemStack.getType().equals(Material.FISHING_ROD);
        }
    }

}
