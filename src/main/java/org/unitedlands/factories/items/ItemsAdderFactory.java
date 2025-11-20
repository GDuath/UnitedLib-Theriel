package org.unitedlands.factories.items;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.unitedlands.utils.Logger;

import dev.lone.itemsadder.api.CustomStack;
import dev.lone.itemsadder.api.ItemsAdder;

public class ItemsAdderFactory extends BaseItemFactory {

    @Override
    public boolean isItem(ItemStack item1, ItemStack item2) {
        CustomStack customStack1 = CustomStack.byItemStack(item1);
        CustomStack customStack2 = CustomStack.byItemStack(item2);

        if (customStack1 != null) {

            Logger.log(customStack1.getNamespacedID());
            if (customStack2 == null) {
                return false;
            } else {
                Logger.log(customStack2.getNamespacedID());
                return customStack1.matchNamespacedID(customStack2);
            }
        } else {
            if (customStack2 != null) {
                Logger.log(customStack2.getNamespacedID());
                return false;
            } else {
                return item1.isSimilar(item2);
            }
        }
    }

    @Override
    public ItemStack getItemStack(String material, int amount) {

        CustomStack customStack = CustomStack.getInstance(material);
        if (customStack != null) {
            var itemStack = customStack.getItemStack();
            itemStack.setAmount(amount);
            return itemStack;
        } else {
            try {
                var mat = Material.getMaterial(material);
                if (mat != null) {
                    return new ItemStack(mat, amount);
                }
            } catch (Exception ex) {
                Logger.logError("Vanilla material \"" + material + "\" not found");
            }
        }

        return null;
    }

    @Override
    public ItemStack getItemStack(String material, int minAmount, int maxAmount) {

        CustomStack customStack = CustomStack.getInstance(material);
        if (customStack != null) {
            var itemStack = customStack.getItemStack();
            itemStack.setAmount(ThreadLocalRandom.current().nextInt(minAmount, maxAmount + 1));
            return itemStack;
        } else {
            try {
                var mat = Material.getMaterial(material);
                if (mat != null) {
                    return new ItemStack(mat, ThreadLocalRandom.current().nextInt(minAmount, maxAmount + 1));
                }
            } catch (Exception ex) {
                Logger.logError("Vanilla material \"" + material + "\" not found");
            }
        }

        return null;
    }

    @Override
    public boolean isItemInInventory(Inventory inventory, ItemStack item) {
        CustomStack customItem = CustomStack.byItemStack(item);
        for (var contentItem : inventory.getContents()) {
            if (contentItem == null || contentItem.getType() == Material.AIR)
                continue;
            var contentCustomItem = CustomStack.byItemStack(contentItem);
            if (customItem != null && contentCustomItem != null) {
                if (customItem.getNamespacedID().equals(contentCustomItem.getNamespacedID()))
                    return true;
            } else if (customItem == null && contentCustomItem == null) {
                if (item.getType() == contentItem.getType())
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean isValidItem(String itemName) {
        CustomStack customItem = CustomStack.getInstance(itemName);
        if (customItem != null) {
            return true;
        } else {
            try {
                var vanillaMaterial = Material.valueOf(itemName);
                if (vanillaMaterial != null)
                    return true;
            } catch (Exception ignore) {
                return false;
            }
        }

        return false;
    }

    @Override
    public String getFilterName(ItemStack itemStack) {
        CustomStack customItem = CustomStack.byItemStack(itemStack);
        if (customItem != null) {
            return customItem.getNamespacedID();
        } else {
            return itemStack.getType().toString();
        }
    }

    @Override
    public List<String> getItemList() {
        var items = Arrays.stream(Material.values())
                .map(Enum::name) // gets the name as a String
                .collect(Collectors.toList());
        var customItems = ItemsAdder.getAllItems().stream().filter(i -> !i.getNamespace().startsWith("_"))
                .map(i -> i.getNamespacedID()).collect(Collectors.toList());
        items.addAll(customItems);
        return items;
    }

}
