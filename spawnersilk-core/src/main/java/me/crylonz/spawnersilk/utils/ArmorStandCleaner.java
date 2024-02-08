package me.crylonz.spawnersilk.utils;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

import java.util.ArrayList;

public class ArmorStandCleaner implements Runnable {
    ArrayList<ArmorStand> armorStands;

    public ArmorStandCleaner(ArrayList<ArmorStand> armorStands) {
        this.armorStands = armorStands;
    }

    @Override
    public void run() {
        armorStands.forEach(Entity::remove);
    }
}
