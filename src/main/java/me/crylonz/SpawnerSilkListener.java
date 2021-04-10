package me.crylonz;

import org.bukkit.ChatColor;
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

import static me.crylonz.SpawnerSilk.*;

public class SpawnerSilkListener implements Listener {

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

                if (p.getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH) || !needSilkTouch) {

                    int random = new Random().nextInt(100);

                    if(dropChance >= random) {
                        CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
                        EntityType entity = spawner.getSpawnedType();
                        ItemStack spawnerItem = SpawnerAPI.getSpawner(entity);

                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), spawnerItem);
                        e.setExpToDrop(0);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent e) {

        if (e.getBlockPlaced().getType() == getSpawnerMaterial()) {
            CreatureSpawner cs = (CreatureSpawner) e.getBlockPlaced().getState();
            cs.setSpawnedType(SpawnerAPI.getEntityType(e.getItemInHand()));
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

        if (!spawnersCanBeModifiedByEgg) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getClickedBlock() != null && e.getClickedBlock().getType() == getSpawnerMaterial()) {
                    e.setCancelled(true);
                }
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
                if (block.getType() == getSpawnerMaterial() && randomInt <= explosionDropChance) {
                    CreatureSpawner s = (CreatureSpawner) block.getState();
                    block.getWorld().dropItemNaturally(block.getLocation(), SpawnerAPI.getSpawner(s.getSpawnedType()));
                }
            }
        }
    }
}
