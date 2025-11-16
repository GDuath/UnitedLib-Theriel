package org.unitedlands.factories.items;

import org.bukkit.inventory.ItemStack;

public interface IItemFactory {
    boolean isItem(ItemStack item1, ItemStack item2);
    ItemStack getItemStack(String material, int amount);
    ItemStack getItemStack(String material, int minAmount, int maxAmount);
}