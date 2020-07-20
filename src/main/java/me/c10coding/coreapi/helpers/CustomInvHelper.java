package me.c10coding.coreapi.helpers;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CustomInvHelper {

    public List<Integer> getIndexesInColumn(Inventory inv, int inventoryIndex, int inventoryNumRows){
        List<Integer> indexes = new ArrayList<>();
        int columnNum = getColumnNum(inventoryNumRows, inventoryIndex, inv);
        int invIndex = 0;
        for(int row = 0; row < inventoryNumRows; row++){
            for(int column = 0; column < 9; column++){
                if(column == columnNum){
                    indexes.add(invIndex);
                }
                invIndex++;
            }
        }
        return indexes;
    }

    public int getColumnNum(int inventoryRows, int targetIndex, Inventory inv){
        int currentIndex = 0;
        for(int x = 0; x < inventoryRows; x++){
            for(int c = 0; c < 9; c++){
                if(currentIndex == targetIndex){
                    return c;
                }
                currentIndex++;
            }
        }
        return 0;
    }

    public List<Integer> getIndexesAroundItem(Inventory inv, int inventoryRows, int itemIndex){
        List<Integer> indexes = new ArrayList<>();
        //The column directly to the left of the item.
        int leftColumnIndex = getColumnNum(inventoryRows, itemIndex-1, inv);
        int invIndex = 0;
        /*
        Loops through all the indexes and gets the indexes that are in the same column as leftColumnIndex variable
         */
        for(int rows = 0; rows < inventoryRows; rows++){
            for(int x = 0; x < 9; x++){
                if(leftColumnIndex == getColumnNum(inventoryRows, invIndex, inv)){
                    indexes.add(invIndex);
                }
                invIndex++;
            }
        }

        invIndex = 0;
        int itemColumnIndex = getColumnNum(inventoryRows, itemIndex, inv);

        for(int rows = 0; rows < inventoryRows; rows++){
            for(int x = 0; x < 9; x++){
                if(invIndex != itemIndex){
                    if(itemColumnIndex == getColumnNum(inventoryRows, invIndex, inv)){
                        indexes.add(invIndex);
                    }
                }
                invIndex++;
            }
        }

        invIndex = 0;
        int rightColumnIndex = getColumnNum(inventoryRows, itemIndex+1, inv);

        for(int rows = 0; rows < inventoryRows; rows++){
            for(int x = 0; x < 9; x++){
                if(rightColumnIndex == getColumnNum(inventoryRows, invIndex, inv)){
                    indexes.add(invIndex);
                }
                invIndex++;
            }
        }

        return indexes;

    }

    public boolean isWithinInventory(Inventory inv, Event e){
        if(e instanceof InventoryClickEvent /*|| e instanceof InventoryMoveItemEvent*/) {
            //if(e instanceof InventoryClickEvent){
            InventoryClickEvent inventoryClickEvent = (InventoryClickEvent) e;
            if (inventoryClickEvent.getClickedInventory() == null) return false;
            if (!inventoryClickEvent.getClickedInventory().equals(inv)) return false;
            return true;
        /*}else if(e instanceof InventoryMoveItemEvent){
                InventoryMoveItemEvent inventoryClickEvent = (InventoryMoveItemEvent) e;
                if(inventoryClickEvent.getDestination() == null) return false;
                if(!inventoryClickEvent.getDestination().equals(inv)) return false;
                return true;
            }*/
        }
        return false;
    }

    public boolean isValidClickedItem(InventoryClickEvent e){
        return (e.getCurrentItem() != null || !e.getCurrentItem().getType().equals(Material.AIR));
    }

}
