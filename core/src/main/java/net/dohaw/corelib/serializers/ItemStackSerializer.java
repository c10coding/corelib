package net.dohaw.corelib.serializers;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ItemStackSerializer {

    private static Map<String, Object> getItemInfo(ItemStack is, int numSlot){

        Map<String, Object> itemInfo = new HashMap<>();
        ItemMeta meta = is.getItemMeta();

        String name = is.getItemMeta().getDisplayName();
        //String mat = mat.toString();
        int amount = is.getAmount();
        short durability = is.getDurability();
        List<String> lore = meta.getLore();
        Map<Enchantment, Integer> enchants;
        Material mat = is.getType();

        if(meta.hasEnchants()){
            if(meta instanceof EnchantmentStorageMeta){
                enchants = ((EnchantmentStorageMeta) meta).getStoredEnchants();
            }else{
                enchants = meta.getEnchants();
            }
        }else{
            enchants = null;
        }

        itemInfo.put("DisplayName", name);
        itemInfo.put("Material", mat);
        itemInfo.put("Amount", amount);
        itemInfo.put("Durability", durability);
        itemInfo.put("Lore", lore);
        itemInfo.put("Enchants", enchants);
        itemInfo.put("Slot", numSlot);

        return itemInfo;

    }

    public static void storeItems(FileConfiguration config, Map<Integer, ItemStack> items, UUID playerUUID){
        for(Map.Entry item : items.entrySet()){
            int numSlot = (int) item.getKey();
            ItemStack is = (ItemStack) item.getValue();
            Map<String, Object> itemInfo = getItemInfo(is, numSlot);
            for(Map.Entry info : itemInfo.entrySet()){
                String key = (String) info.getKey();
                String value = (String) info.getKey();
                String path = "Items." + playerUUID.toString() + "." + numSlot + "." + key;
                //loadedFile.set(path, );
            }

        }
    }

    public static ItemStack lineToItemStack(String line){
        // item_name; 1
        String[] arr = line.split(";");
        String itemName = arr[0].trim();
        int amount = Integer.parseInt(arr[1].trim());
        Material mat = Material.valueOf(itemName);
        return new ItemStack(mat, amount);
    }



}
