package org.unitedlands.factories.items;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.unitedlands.utils.Logger;

public class VanillaItemFactory extends BaseItemFactory {

    @Override
    public boolean isItem(ItemStack item1, ItemStack item2) {
        return item1.isSimilar(item2);
    }

    @Override
    public ItemStack getItemStack(String material, int amount) {
        try {
            var mat = Material.getMaterial(material);
            if (mat != null) {
                return new ItemStack(mat, amount);
            }
        } catch (Exception ex) {
            Logger.logError("Error creating item stack: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public ItemStack getItemStack(String material, int minAmount, int maxAmount) {
        try {
            var mat = Material.getMaterial(material);
            if (mat != null) {
                return new ItemStack(mat, ThreadLocalRandom.current().nextInt(minAmount, maxAmount + 1));
            }
        } catch (Exception ex) {
            Logger.logError("Error creating item stack: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean isItemInInventory(Inventory inventory, ItemStack item) {
        return inventory.contains(item.getType());
    }

    @Override
    public boolean isValidItem(String itemName) {
        var vanillaMaterial = Material.valueOf(itemName);
        if (vanillaMaterial != null)
            return true;
        return false;
    }

    @Override
    public String getFilterName(ItemStack itemStack) {
        return itemStack.getType().toString();
    }

    @Override
    public List<String> getItemList() {
        return Arrays.stream(Material.values())
                .map(Enum::name) // gets the name as a String
                .collect(Collectors.toList());
    }
}
