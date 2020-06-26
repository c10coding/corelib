package me.c10coding.coreapi.menus;

import me.c10coding.coreapi.CoreAPI;
import me.c10coding.coreapi.chat.ChatFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Menu implements InventoryHolder {

        protected final Inventory inv;
        protected Material fillerMat = Material.STAINED_GLASS_PANE;
        protected byte variant = (byte) 1000;
        protected JavaPlugin plugin;
        private ChatFactory chatFactory = CoreAPI.getInstance().getChatFactory();

        public Menu(String menuTitle, int numSlots) {
            this.inv = Bukkit.createInventory(this, numSlots, chatFactory.colorString(menuTitle));
        }

        public Menu(JavaPlugin plugin, String menuTitle, int numSlots){
            this.inv = Bukkit.createInventory(this, numSlots, chatFactory.colorString(menuTitle));
            this.plugin = plugin;
        }

        public Inventory getInventory() {
            return inv;
        }

        protected void setFillerMaterial(Material fillerMat){
            this.fillerMat = fillerMat;
        }

        protected void setVariant(byte variant){
            this.variant = variant;
        }

        public void openInventory(Player p) {
            p.openInventory(inv);
        }

        protected void fillMenu(boolean hasBackButton){
            for(int x = 0; x < inv.getSize(); x++){
                if(inv.getItem(x) == null){
                    inv.setItem(x, createGuiItem());
                }
            }

            if(hasBackButton){
                ItemStack backButton = new ItemStack(Material.REDSTONE_TORCH_ON);
                ItemMeta backButtonMeta = backButton.getItemMeta();
                backButtonMeta.setDisplayName(chatFactory.colorString("&eClick me to go back!"));
                backButton.setItemMeta(backButtonMeta);
                inv.setItem(inv.getSize() - 1, backButton);
            }

        }

        /*
         * If the item has enchantments
         */
        protected ItemStack createGuiItem(Material mat, String name, Map<Enchantment,Integer> enchants, int amount, List<String> lore) {

            ItemStack item;
            if(variant != 1000){
                item = new ItemStack(mat, amount, variant);
            }else{
                item = new ItemStack(mat, amount);
            }


            ItemMeta meta = item.getItemMeta();
            ArrayList<Enchantment> itemEnchants = new ArrayList<>();
            ArrayList<Integer> itemLevels = new ArrayList<>();

            //Gets all the enchants from the map
            for(Map.Entry<Enchantment, Integer> en : enchants.entrySet()){
                itemEnchants.add(en.getKey());
            }

            //Gets all the levels from the map
            for(Map.Entry<Enchantment, Integer> en : enchants.entrySet()){
                itemLevels.add(en.getValue());
            }

            for(int x = 0; x < enchants.size(); x++) {

                if(!item.getType().equals(Material.BOOK)) {
                    meta.addEnchant(itemEnchants.get(x), itemLevels.get(x), true);
                }else {
                    EnchantmentStorageMeta metaE = (EnchantmentStorageMeta) item.getItemMeta();
                    metaE.addStoredEnchant(itemEnchants.get(x), itemLevels.get(x), true);
                    item.setItemMeta(metaE);
                }

            }

            meta.setDisplayName(name);
            item.setAmount(amount);

            ArrayList<String> metaLore = new ArrayList<>();

            for(String lorecomments : lore) {
                metaLore.add(lorecomments);
            }

            if(!item.getType().equals(Material.ENCHANTED_BOOK)) {
                meta.setLore(metaLore);
                item.setItemMeta(meta);
            }

            return item;
        }

        /*
         * No enchants. Regular items
         */
        protected ItemStack createGuiItem(Material material, String name,int amount, List<String> lore) {

            ItemStack item;
            if(variant != 1000){
                item = new ItemStack(material, amount, variant);
            }else{
                item = new ItemStack(material, amount);
            }

            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            item.setAmount(amount);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            ArrayList<String> metaLore = new ArrayList<>();

            if(lore.size() != 0) {
                for(String lorecomments : lore) {
                    metaLore.add(lorecomments);
                }
                meta.setLore(metaLore);
            }else {
                meta.setLore(null);
            }

            item.setItemMeta(meta);
            return item;

        }

        //If you only want one item
        protected ItemStack createGuiItem(Material material, String name, List<String> lore) {

            ItemStack item;
            if(variant != 1000){
                item = new ItemStack(material, 1, variant);
            }else{
                item = new ItemStack(material, 1);
            }

            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);

            ArrayList<String> metaLore = new ArrayList<>();

            for(String lorecomments : lore) {
                metaLore.add(lorecomments);
            }

            meta.setLore(metaLore);
            item.setItemMeta(meta);
            return item;
        }

        //Filler item
        protected ItemStack createGuiItem() {

            ItemStack item;
            if(variant != 1000){
                item = new ItemStack(fillerMat,1, variant);
            }else{
                item = new ItemStack(fillerMat, 1);
            }

            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            meta.setLore(null);
            item.setItemMeta(meta);

            return item;
        }


        protected int findSlotAmount(List<String> list) {
            int slotSize = 0;

            do {
                slotSize+=9;
            }while(slotSize <= list.size());

            return slotSize;

        }

        protected void fillMenu(List<String> list) {
            int invSlots = inv.getSize();
            int amount = list.size();

            for(int x = amount; x < invSlots; x++) {
                inv.setItem(x, createGuiItem());
                if(x == (invSlots - 1)) {
                    List<String> lore = new ArrayList<>();
                    lore.add(chatFactory.colorString("&rClick me to go back to the"));
                    lore.add(chatFactory.colorString("&rlast menu!"));
                    inv.setItem(x, createGuiItem(Material.ARROW, chatFactory.colorString("&6Go back"), lore));
                }
            }
        }

        /*
         * You use this when you already know how many items are in the menu
         */
        protected void fillMenu(int amount) {
            int invSlots = inv.getSize();

            for(int x = amount; x < invSlots; x++) {
                inv.setItem(x, createGuiItem());
                if(x == (invSlots - 1)) {
                    List<String> lore = new ArrayList<>();
                    lore.add(chatFactory.colorString("&rClick me to go back to the"));
                    lore.add(chatFactory.colorString("&rlast menu!"));
                    inv.setItem(x, createGuiItem(Material.REDSTONE_TORCH_ON, chatFactory.colorString("&6Go back"), lore));
                }
            }
        }


        public abstract void initializeItems(Player p);

        @EventHandler
        protected abstract void onInventoryClick(InventoryClickEvent e);

}

