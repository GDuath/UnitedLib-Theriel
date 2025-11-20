package org.unitedlands.factories.items;

import java.util.List;

import org.bukkit.inventory.Inventory;
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

    @Override
    public abstract List<String> getItemList();

    @Override
    public abstract boolean isValidItem(String itemName);

    @Override
    public abstract String getFilterName(ItemStack itemStack);

    @Override
    public abstract boolean isItemInInventory(Inventory inventory, ItemStack item);
}
