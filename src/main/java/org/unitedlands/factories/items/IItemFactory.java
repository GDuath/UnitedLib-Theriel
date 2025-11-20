package org.unitedlands.factories.items;

import java.util.List;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface IItemFactory {
    boolean isItem(ItemStack item1, ItemStack item2);
    ItemStack getItemStack(String material, int amount);
    ItemStack getItemStack(String material, int minAmount, int maxAmount);

    List<String> getItemList();
    boolean isValidItem(String itemName);
    String getFilterName(ItemStack itemStack);
    boolean isItemInInventory(Inventory inventory, ItemStack item);
}