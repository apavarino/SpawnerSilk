package me.crylonz;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

import static me.crylonz.SpawnerSilk.getSpawnerMaterial;
import static me.crylonz.SpawnerSilk.playersUUID;

public class SpawnerSilkListener implements Listener {

    private SpawnerSilk plugin;

    public SpawnerSilkListener(SpawnerSilk plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        playersUUID.put(e.getPlayer().getName(), e.getPlayer().getUniqueId().toString());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        playersUUID.remove(e.getPlayer().getName(), e.getPlayer().getUniqueId().toString());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreakEvent(BlockBreakEvent e) {

        if (!e.isCancelled()) {

            if (e.getBlock().getType() == getSpawnerMaterial() && e.getPlayer().hasPermission("spawnersilk.minespawner")) {
                Player p = e.getPlayer();

                if ((p.getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)
                        || !plugin.getDataConfig().getBoolean("need-silk-touch")) && canGetSpawner(p)) {

                    int randomSpawnerDrop = new Random().nextInt(100);
                    int randomEggDrop = new Random().nextInt(100);

                    dropToPlayer(e,
                            plugin.getDataConfig().getInt("drop-chance") >= randomSpawnerDrop,
                            plugin.getDataConfig().getInt("drop-egg-chance") >= randomEggDrop);
                }
            }
        }
    }

    public void dropToPlayer(BlockBreakEvent e, boolean dropSpawner, boolean dropEgg) {
        CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
        EntityType entity = spawner.getSpawnedType();
        ItemStack spawnerItem = SpawnerAPI.getSpawner(entity);

        e.setExpToDrop(0);

        int dropMode = plugin.getDataConfig().getInt("drop-mode");
        boolean dropInCreative = plugin.getDataConfig().getBoolean("drop-in-creative");

      if(e.getPlayer().getGameMode() == GameMode.CREATIVE && !dropInCreative) {
          return;
      }

        if (dropMode == 1) {

            if (plugin.getDataConfig().getBoolean("drop-to-inventory") && e.getPlayer().getInventory().firstEmpty() != -1) {

                if (dropSpawner) {
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.SPAWNER));
                }

                if (dropEgg) {
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.valueOf(entity.name().toUpperCase().replace(" ", "_") + "_SPAWN_EGG")));
                }

            } else {
                if (dropSpawner) {
                    e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.SPAWNER));
                }
                if (dropEgg) {
                    e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                            new ItemStack(Material.valueOf(entity.name().toUpperCase().replace(" ", "_") + "_SPAWN_EGG")));
                }
            }
        }
        // Drop Mode 0
        else {
            if (plugin.getDataConfig().getBoolean("drop-to-inventory") && e.getPlayer().getInventory().firstEmpty() != -1) {
                if (dropSpawner) {
                    e.getPlayer().getInventory().addItem(spawnerItem);
                }
            } else {
                if (dropSpawner) {
                    e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(), spawnerItem);
                }
            }
        }
    }

    public boolean canGetSpawner(Player p) {
        int mode = plugin.getDataConfig().getInt("pickaxe-mode");
        boolean valid = mode == 0;
        if (mode <= 1 && !valid) {
            valid = p.getInventory().getItemInMainHand().getType() == Material.WOODEN_PICKAXE;
        }
        if (mode <= 2 && !valid) {
            valid = p.getInventory().getItemInMainHand().getType() == Material.STONE_PICKAXE;
        }
        if (mode <= 3 && !valid) {
            valid = p.getInventory().getItemInMainHand().getType() == Material.IRON_PICKAXE;
        }
        if (mode <= 4 && !valid) {
            valid = p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_PICKAXE;
        }
        if (mode <= 5 && !valid) {
            valid = p.getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE;
        }
        if (mode <= 6 && !valid) {
            valid = p.getInventory().getItemInMainHand().getType() == Material.NETHERITE_PICKAXE;
        }
        return valid;
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {

        if (e.getBlockPlaced().getType() == getSpawnerMaterial()) {
            CreatureSpawner cs = (CreatureSpawner) e.getBlockPlaced().getState();
            EntityType entityType = SpawnerAPI.getEntityType(e.getItemInHand());

            if (entityType != EntityType.UNKNOWN) {
                cs.setSpawnedType(SpawnerAPI.getEntityType(e.getItemInHand()));
            } else {
                cs.setSpawnedType(EntityType.PIG);
            }
            cs.update();
        }
    }

    @EventHandler
    public void playerRenameItem(InventoryClickEvent event) {
        if (event.getInventory().getType().equals(InventoryType.ANVIL)) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() == getSpawnerMaterial()) {
                event.getWhoClicked().sendMessage(ChatColor.RED + " You can't put that in an anvil");
                event.setResult(Event.Result.DENY);
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (!plugin.getDataConfig().getBoolean("spawners-can-be-modified-by-egg")) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getClickedBlock() != null && e.getClickedBlock().getType() == getSpawnerMaterial()) {
                    e.setCancelled(true);
                }
            }
        } else {
            if (!plugin.getDataConfig().getBoolean("use-egg") && e.getItem() != null &&
                    e.getPlayer().getTargetBlock(null, 5).getType() == getSpawnerMaterial() &&
                    e.getItem().getType().name().toUpperCase().contains("EGG")) {
                e.setCancelled(true);
                CreatureSpawner cs = (CreatureSpawner) e.getPlayer().getTargetBlock(null, 5).getState();
                cs.setSpawnedType(EntityType.valueOf(e.getItem().getType().name().replace("_SPAWN_EGG", "")));
                cs.update();
            }
        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        Random r = new Random();
        int randomInt = r.nextInt(100);
        if (e.blockList().size() > 0) {
            for (int i = 0; i < e.blockList().size(); i++) {
                Block block = e.blockList().get(i);
                if (block.getType() == getSpawnerMaterial() && randomInt <= plugin.getDataConfig().getInt("explosion-drop-chance")) {
                    CreatureSpawner s = (CreatureSpawner) block.getState();
                    block.getWorld().dropItemNaturally(block.getLocation(), SpawnerAPI.getSpawner(s.getSpawnedType()));
                }
            }
        }
    }
}
