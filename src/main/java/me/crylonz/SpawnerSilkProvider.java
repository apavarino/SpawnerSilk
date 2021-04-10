package me.crylonz;

import net.brcdev.shopgui.spawner.external.provider.ExternalSpawnerProvider;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class SpawnerSilkProvider implements ExternalSpawnerProvider {
    @Override
    public String getName() {
        return "SpawnerSilk";
    }

    @Override
    public ItemStack getSpawnerItem(EntityType entityType) {
        return SpawnerAPI.getSpawner(entityType);
    }

    @Override
    public EntityType getSpawnerEntityType(ItemStack itemStack) {
        return SpawnerAPI.getEntityType(itemStack);
    }
}
