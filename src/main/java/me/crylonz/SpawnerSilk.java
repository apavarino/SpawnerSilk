package me.crylonz;

import me.crylonz.command.EditSpawnerCommandExecutor;
import me.crylonz.command.GiveSpawnerCommandExecutor;
import me.crylonz.command.SpawnerSilkTabCompletion;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

public class SpawnerSilk extends JavaPlugin implements Listener {

    public static final Logger log = Logger.getLogger("Minecraft");
    public static HashMap<String, String> playersUUID = new HashMap<>();
    private SpawnerSilkProvider spawnerProvider;
    public File configFile = new File(getDataFolder(), "config.yml");

    //config data
    public static boolean needSilkTouch = true;
    public static boolean spawnersCanBeModifiedByEgg = true;
    public static double dropChance = 10;
    public static double explosionDropChance = 5;

    public void onEnable() {

        Objects.requireNonNull(getCommand("givespawner")).setTabCompleter(new SpawnerSilkTabCompletion());
        Objects.requireNonNull(getCommand("editspawner")).setTabCompleter(new SpawnerSilkTabCompletion());

        for (Player play : Bukkit.getOnlinePlayers()) {
            playersUUID.put(play.getName(), play.getUniqueId().toString());
        }

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new SpawnerSilkListener(), this);

        Metrics metrics = new Metrics(this, 5536);

        this.getCommand("givespawner").setExecutor(new GiveSpawnerCommandExecutor());
        this.getCommand("editspawner").setExecutor(new EditSpawnerCommandExecutor());

        if (Bukkit.getPluginManager().getPlugin("ShopGUIPlus") != null) {
            this.spawnerProvider = new SpawnerSilkProvider();
            hookIntoShopGui();
        }

        if (!configFile.exists()) {
            saveDefaultConfig();
        } else {
            autoUpdateConfigFile();

            needSilkTouch = getConfig().getBoolean("needSilkTouch");
            spawnersCanBeModifiedByEgg = getConfig().getBoolean("spawnersCanBeModifiedByEgg");
            explosionDropChance = getConfig().getDouble("explosionDropChance");
            dropChance = getConfig().getDouble("dropChance");
        }
    }

    private void hookIntoShopGui() {

        try {
            net.brcdev.shopgui.ShopGuiPlusApi.registerSpawnerProvider(spawnerProvider);
        } catch (net.brcdev.shopgui.exception.api.ExternalSpawnerProviderNameConflictException e) {
            this.getLogger().warning("Failed to hook into ShopGUI+: " + e.getMessage());
        }
    }

    public void onDisable() {

    }

    public static Material getSpawnerMaterial() {
        if (Bukkit.getVersion().contains("1.12")) {
            return Material.getMaterial("MOB_SPAWNER");
        }
        return Material.SPAWNER;
    }

    // Add missing parameters on config.yml
    public void autoUpdateConfigFile() {
        reloadConfig();
        ArrayList<String> allConfigPath = new ArrayList<>();
        allConfigPath.add("needSilkTouch");
        allConfigPath.add("spawnersCanBeModifiedByEgg");
        allConfigPath.add("explosionDropChance");
        allConfigPath.add("dropChance");

        for (String path : allConfigPath) {
            FileConfiguration configuration = YamlConfiguration.loadConfiguration(configFile);
            if (configuration.get(path) == null) {
                File file = new File(getDataFolder().getAbsolutePath() + File.separator + "config.yml");
                File oldFile = new File(getDataFolder().getAbsolutePath() + File.separator + "config.old.yml");
                file.renameTo(oldFile);
                saveDefaultConfig();
                log.warning("[SpawnerSilk] Configuration update detected ! Don't forget to update your config.yml");
                break;
            }
        }
    }
}
