package me.c10coding.coreapi.enchantmentregisters;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnchantmentRegister_v1_15_2 {

    private List<Enchantment> enchantmentList = new ArrayList<>();

    public void addEnchantment(Enchantment e){
        enchantmentList.add(e);
    }

    public void registerEnchantment(Enchantment ench) {
        //Using Reflection
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(ench);
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void unRegisterEnchantments(){
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            for(Enchantment e : enchantmentList) {
                byKey.remove(e.getKey());
            }

            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            for(Enchantment e : enchantmentList) {
                byName.remove(e.getName());
            }

        } catch (Exception ignored) { }
    }

    public void registerEnchantments(){
        for(Enchantment e : enchantmentList){
            registerEnchantment(e);
        }
    }

}
