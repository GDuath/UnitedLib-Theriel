package org.unitedlands.factories.items;

import org.bukkit.inventory.ItemStack;

public abstract class BaseItemFactory implements IItemFactory {

    public BaseItemFactory() {

    }

    @Override
    public abstract boolean isItem(ItemStack item1, ItemStack item2);

    @Override
    public abstract ItemStack getItemStack(String material, int amount);

    @Override
    public abstract ItemStack getItemStack(String material, int minAmount, int maxAmount);

}
