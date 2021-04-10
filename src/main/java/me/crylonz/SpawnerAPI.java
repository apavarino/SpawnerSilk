package me.crylonz;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.logging.Logger;

public class SpawnerAPI {

    private final static Logger log = Logger.getLogger("Minecraft");

    public static ItemStack getSpawner(EntityType entity) {

        ItemStack spawnerItem;
        spawnerItem = new ItemStack(SpawnerSilk.getSpawnerMaterial(), 1);

        ItemMeta spawnerItemMeta = spawnerItem.getItemMeta();
        String name = entity.name() + " Spawner";

        spawnerItemMeta.setDisplayName(ChatColor.YELLOW + name);
        spawnerItem.setItemMeta(spawnerItemMeta);

        return spawnerItem;
    }

    public static EntityType getEntityType(ItemStack itemStack) {

        EntityType entity = EntityType.UNKNOWN;
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemStack.getType() == SpawnerSilk.getSpawnerMaterial() && itemMeta != null) {

            String itemMetaName = ChatColor.stripColor(itemMeta.getDisplayName().replace(" Spawner", ""));
            itemMetaName = itemMetaName.replaceAll(" ", "_");

            try {
                entity = EntityType.valueOf(itemMetaName.toUpperCase());
            } catch (IllegalArgumentException e) {
                return EntityType.UNKNOWN;
            }
        }
        return entity;
    }
}