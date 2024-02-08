package me.crylonz.spawnersilk.utils;

import me.crylonz.spawnersilk.SpawnerSilk;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.UUID;

public class SpawnerSilkHologram {

    /**
     * Generate a hologram at the given position
     *
     * @param location position to place
     * @param text     text to display
     * @param shiftX   x shifting
     * @param shiftY   y shifting
     * @param shiftZ   z shifting
     * @return the generated armorstand
     */
    public static ArmorStand generateHologram(Location location,
                                              String text,
                                              float shiftX,
                                              float shiftY,
                                              float shiftZ,
                                              SpawnerSilk plugin,
                                              UUID player) {
        if (location != null && location.getWorld() != null) {
            Location holoLoc = new Location(location.getWorld(),
                    location.getX() + shiftX,
                    location.getY() + shiftY + 2,
                    location.getZ() + shiftZ);

            ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(holoLoc, EntityType.ARMOR_STAND);
            armorStand.setInvulnerable(true);
            armorStand.setSmall(true);
            armorStand.setGravity(false);
            armorStand.setCanPickupItems(false);
            armorStand.setVisible(false);
            armorStand.setCollidable(false);
            armorStand.setMetadata("spawnerSilk", new FixedMetadataValue(plugin, player));
            armorStand.setCustomName(text);
            armorStand.setSilent(true);
            armorStand.setMarker(true);
            armorStand.setCustomNameVisible(true);
            return armorStand;
        }
        return null;
    }



}

