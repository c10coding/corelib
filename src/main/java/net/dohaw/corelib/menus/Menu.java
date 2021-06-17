package net.dohaw.corelib.menus;

import net.dohaw.corelib.StringUtils;
import net.dohaw.corelib.helpers.CustomInvHelper;
import net.dohaw.corelib.helpers.ItemStackHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Menu implements InventoryHolder {

    protected final Inventory inv;
    protected Material fillerMat;
    protected Material backMat;
    protected byte variant = (byte) 1000;
    protected JavaPlugin plugin;
    protected Menu previousMenu;

    public Menu(JavaPlugin plugin, Menu previousMenu, String menuTitle, int numSlots){
        this.inv = Bukkit.createInventory(this, numSlots, StringUtils.colorString(menuTitle));
        this.plugin = plugin;
        this.previousMenu = previousMenu;
    }

    public Inventory getInventory() {
        return inv;
    }

    public void clearItems(){
        inv.clear();
    }

    protected void setFillerMaterial(Material fillerMat){
        this.fillerMat = fillerMat;
    }

    protected void goToPreviousMenu(Player player){
        player.closeInventory();
        previousMenu.openInventory(player);
    }

    protected void setBackMaterial(Material backMat){
        this.backMat = backMat;
    }

    protected void setVariant(byte variant){
        this.variant = variant;
    }

    public void openInventory(Player p) {
        p.openInventory(inv);
    }

    /*
     * If the item has enchantments
     */
    protected ItemStack createGuiItem(Material mat, String name, Map<Enchantment,Integer> enchants, int amount, List<String> lore) {

        name = StringUtils.colorString(name);
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

        List<String> metaLore = new ArrayList<>();

        for(String lorecomments : lore) {
            metaLore.add(lorecomments);
        }

        metaLore = StringUtils.colorLore(metaLore);

        if(!item.getType().equals(Material.ENCHANTED_BOOK)) {
            meta.setLore(metaLore);
            item.setItemMeta(meta);
        }

        return item;
    }

    /*
     * No enchants. Regular items. Supports Player Heads
     */
    protected ItemStack createGuiItem(Material material, String name, int amount, List<String> lore) {

        name = StringUtils.colorString(name);
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

        if(lore.size() != 0) {
            meta.setLore(lore);
        }else{
            meta.setLore(null);
        }

        item.setItemMeta(meta);
        return item;

    }

    protected ItemStack createGuiItem(ItemStack stack, String name, List<String> lore){

        ItemStack item = stack.clone();
        name = StringUtils.colorString(name);
        lore = StringUtils.colorLore(lore);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        item.setItemMeta(meta);
        return item;

    }

    //If you only want one item
    protected ItemStack createGuiItem(Material material, String name, List<String> lore) {

        name = StringUtils.colorString(name);
        lore = StringUtils.colorLore(lore);

        ItemStack item;
        if(variant != 1000){
            item = new ItemStack(material, 1, variant);
        }else{
            item = new ItemStack(material, 1);
        }

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);

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

    private ItemStack createBackButton(){

        ItemStack backButton = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = backButton.getItemMeta();

        List<String> lore = new ArrayList<>();
        lore.add(StringUtils.colorString("&rClick me to go back to the"));
        lore.add(StringUtils.colorString("&rlast menu!"));

        String displayName = StringUtils.colorString("&6Go back");
        meta.setLore(lore);
        meta.setDisplayName(displayName);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        backButton.setItemMeta(meta);
        return backButton;
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
                inv.setItem(x, createBackButton());
            }
        }
    }

    protected void fillMenu(boolean hasBackButton){

        for(int x = 0; x < inv.getSize(); x++){
            if(inv.getItem(x) == null){
                inv.setItem(x, createGuiItem());
            }
        }

        if(hasBackButton){
            inv.setItem(inv.getSize() - 1, createBackButton());
        }

    }

    protected void fillMenu(boolean hasBackButton, int... exclusions){

        List<Integer> exlusionsList = Arrays.stream(exclusions)        // IntStream
            .boxed()          // Stream<Integer>
            .collect(Collectors.toList());
        for(int x = 0; x < inv.getSize(); x++){
            if(inv.getItem(x) == null && !exlusionsList.contains(x)){
                inv.setItem(x, createGuiItem());
            }
        }

        if(hasBackButton){
            ItemStack backButton = new ItemStack(backMat);
            ItemMeta backButtonMeta = backButton.getItemMeta();
            backButtonMeta.setDisplayName(StringUtils.colorString("&eClick me to go back!"));
            backButton.setItemMeta(backButtonMeta);
            inv.setItem(inv.getSize() - 1, backButton);
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
                lore.add(StringUtils.colorString("&rClick me to go back to the"));
                lore.add(StringUtils.colorString("&rlast menu!"));
                inv.setItem(x, createBackButton());
            }
        }
    }

    protected void fillRow(int rowIndex){
        int startingIndex = rowIndex * 9;
        int endingIndex = startingIndex + 9;
        for(int x = startingIndex; x < endingIndex; x++){
            inv.setItem(x, createGuiItem());
        }
    }

    protected void fillRow(int rowIndex, List<Integer> exclusions){
        int startingIndex = rowIndex * 9;
        int endingIndex = startingIndex + 9;
        for(int x = startingIndex; x < endingIndex; x++){
            if(!exclusions.contains(x)){
                inv.setItem(x, createGuiItem());
            }
        }
    }

    public abstract void initializeItems(Player p);

    protected boolean isBackButton(ItemStack stack){
        if(stack == null || stack.getItemMeta() == null) return false;
        ItemMeta meta = stack.getItemMeta();
        return meta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS) && stack.getType() == backMat;
    }

    @EventHandler
    protected abstract void onInventoryClick(InventoryClickEvent e);

}

