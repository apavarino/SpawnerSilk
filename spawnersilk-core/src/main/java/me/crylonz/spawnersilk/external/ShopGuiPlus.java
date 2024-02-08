package me.crylonz.spawnersilk.external;

import me.crylonz.spawnersilk.SpawnerSilkProvider;
import net.brcdev.shopgui.exception.api.ExternalSpawnerProviderNameConflictException;

import java.util.logging.Logger;

public class ShopGuiPlus {

    public static void hookIntoShopGui(SpawnerSilkProvider spawnerProvider, Logger logger) {
        try {
            net.brcdev.shopgui.ShopGuiPlusApi.registerSpawnerProvider(spawnerProvider);
        } catch (ExternalSpawnerProviderNameConflictException e) {
            logger.severe("ShopGUI+ support disabled because it can't be load");
        }
    }
}
