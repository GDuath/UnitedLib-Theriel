package org.unitedlands.factories.items;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.unitedlands.utils.Formatter;
import org.unitedlands.utils.Logger;

import com.nexomc.nexo.api.NexoBlocks;
import com.nexomc.nexo.api.NexoFurniture;
import com.nexomc.nexo.api.NexoItems;
import com.nexomc.nexo.items.ItemBuilder;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class NexoFactory extends BaseItemFactory {

    @Override
    public boolean isItem(ItemStack item1, ItemStack item2) {

        ItemBuilder customStack1 = NexoItems.builderFromItem(item1);
        ItemBuilder customStack2 = NexoItems.builderFromItem(item2);

        if (customStack1 != null) {

            Logger.log(NexoItems.idFromItem(customStack1));
            if (customStack2 == null) {
                return false;
            } else {
                Logger.log(NexoItems.idFromItem(customStack2));
                return NexoItems.isSameId(item1, item2);
            }
        } else {
            if (customStack2 != null) {
                Logger.log(NexoItems.idFromItem(customStack2));
                return false;
            } else {
                return item1.isSimilar(item2);
            }
        }

    }

    @Override
    public ItemStack getItemStack(String material, int amount) {
        ItemBuilder customStack = NexoItems.itemFromId(material);
        if (customStack != null) {
            var itemStack = customStack.build();
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
        ItemBuilder customStack = NexoItems.itemFromId(material);
        if (customStack != null) {
            var itemStack = customStack.build();
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
    public List<String> getItemList() {
        var items = Arrays.stream(Material.values())
                .map(Enum::name) // gets the name as a String
                .collect(Collectors.toList());
        var customItems = NexoItems.items().stream().map(i -> NexoItems.idFromItem(i)).collect(Collectors.toList());
        items.addAll(customItems);
        return items;
    }

    @Override
    public boolean isValidItem(String itemName) {
        ItemBuilder customItem = NexoItems.itemFromId(itemName);
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
        ItemBuilder customItem = NexoItems.builderFromItem(itemStack);
        if (customItem != null) {
            return NexoItems.idFromItem(customItem);
        } else {
            return itemStack.getType().toString();
        }
    }

    @Override
    public String getDisplayName(ItemStack itemStack) {
        ItemBuilder customItem = NexoItems.builderFromItem(itemStack);
        if (customItem != null) {
            return Formatter.removeLegacyFormatting(PlainTextComponentSerializer.plainText().serialize(customItem.getItemName()));
        } else {
            return Formatter.removeLegacyFormatting(PlainTextComponentSerializer.plainText().serialize(itemStack.displayName()));
        }
    }

    @Override
    public boolean isItemInInventory(Inventory inventory, ItemStack item) {
        ItemBuilder customItem = NexoItems.builderFromItem(item);
        for (var contentItem : inventory.getContents()) {
            if (contentItem == null || contentItem.getType() == Material.AIR)
                continue;
            var contentCustomItem = NexoItems.builderFromItem(contentItem);
            if (customItem != null && contentCustomItem != null) {
                return NexoItems.isSameId(contentItem, item);
            } else if (customItem == null && contentCustomItem == null) {
                if (item.getType() == contentItem.getType())
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean isCustomItem(ItemStack item) {
        return NexoItems.exists(item);
    }

    @Override
    public String getId(ItemStack itemStack) {
        ItemBuilder customItem = NexoItems.builderFromItem(itemStack);
        if (customItem != null) {
            return NexoItems.idFromItem(customItem);
        } else {
            return itemStack.getType().toString();
        }
    }

    @Override
    public void placeBlock(String id, Location location) {
        try {
            if (NexoBlocks.isCustomBlock(id)) {
                NexoBlocks.place(id, location);
                return;
            }
            if (NexoFurniture.isFurniture(id)) {
                NexoFurniture.place(id, location, 0, BlockFace.DOWN);
                return;
            }

        } catch (Exception ex) {
            Logger.logError("Could not place block " + id);
        }

    }

}
