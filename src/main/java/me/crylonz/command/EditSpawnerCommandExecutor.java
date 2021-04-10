package me.crylonz.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EditSpawnerCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length >= 1 && args.length <= 2) {
                Block block = player.getTargetBlockExact(10);

                if (block != null && block.getType() == Material.SPAWNER) {
                    CreatureSpawner cs = (CreatureSpawner) block.getState();
                    if (cmd.getName().equalsIgnoreCase("editspawner")) {

                        /// SPAWNRANGE
                        if (args[0].equalsIgnoreCase("spawnrange")) {
                            if (args.length == 2) {
                                cs.setSpawnRange(Integer.parseInt(args[1]));
                                cs.update();
                                block.setBlockData(cs.getBlockData());
                            }
                            player.sendMessage(ChatColor.GOLD + "[SpawnerSilk] " + ChatColor.WHITE +
                                    "SpawnRange value set to : " + ChatColor.AQUA + cs.getSpawnRange());
                            return true;
                        }

                        /// SPAWNCOUNT
                        else if (args[0].equalsIgnoreCase("spawncount")) {
                            if (args.length == 2) {
                                cs.setSpawnCount(Integer.parseInt(args[1]));
                                cs.update();
                                block.setBlockData(cs.getBlockData());

                            }
                            player.sendMessage(ChatColor.GOLD + "[SpawnerSilk] " + ChatColor.WHITE +
                                    "SpawnCount value set to : " + ChatColor.AQUA + cs.getSpawnCount());
                            return true;
                        }

                        /// MaxNearbyEntities
                        else if (args[0].equalsIgnoreCase("MaxNearbyEntities")) {
                            if (args.length == 2) {
                                cs.setMaxNearbyEntities(Integer.parseInt(args[1]));
                                cs.update();
                                block.setBlockData(cs.getBlockData());
                            }

                            player.sendMessage(ChatColor.GOLD + "[SpawnerSilk] " + ChatColor.WHITE +
                                    "MaxNearbyEntities value set to : " + ChatColor.AQUA + cs.getMaxNearbyEntities());
                            return true;
                        }

                        /// RequiredPlayerRange
                        else if (args[0].equalsIgnoreCase("RequiredPlayerRange")) {
                            if (args.length == 2) {
                                cs.setRequiredPlayerRange(Integer.parseInt(args[1]));
                                cs.update();
                                block.setBlockData(cs.getBlockData());
                            }

                            player.sendMessage(ChatColor.GOLD + "[SpawnerSilk] " + ChatColor.WHITE +
                                    "RequiredPlayerRange value set to : " + ChatColor.AQUA + cs.getRequiredPlayerRange());
                            return true;
                        }

                        /// Delay
                        else if (args[0].equalsIgnoreCase("Delay")) {
                            if (args.length == 2) {
                                cs.setDelay(Integer.parseInt(args[1]));
                                cs.update();
                                block.setBlockData(cs.getBlockData());
                            }

                            player.sendMessage(ChatColor.GOLD + "[SpawnerSilk] " + ChatColor.WHITE +
                                    "Delay value set to : " + ChatColor.AQUA + cs.getDelay());
                            return true;
                        }

                        /// MaxSpawnDelay
                        else if (args[0].equalsIgnoreCase("MaxSpawnDelay")) {
                            if (args.length == 2) {
                                cs.setMaxSpawnDelay(Integer.parseInt(args[1]));
                                cs.update();
                                block.setBlockData(cs.getBlockData());
                            }

                            player.sendMessage(ChatColor.GOLD + "[SpawnerSilk] " + ChatColor.WHITE +
                                    "MaxSpawnDelay value set to : " + ChatColor.AQUA + cs.getMaxSpawnDelay());
                            return true;
                        }
                        /// MinSpawnDelay
                        else if (args[0].equalsIgnoreCase("MinSpawnDelay")) {
                            if (args.length == 2) {
                                cs.setMinSpawnDelay(Integer.parseInt(args[1]));
                                cs.update();
                                block.setBlockData(cs.getBlockData());
                            }

                            player.sendMessage(ChatColor.GOLD + "[SpawnerSilk] " + ChatColor.WHITE +
                                    "MinSpawnDelay value set to : " + ChatColor.AQUA + cs.getMinSpawnDelay());
                            return true;
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.GOLD + "[SpawnerSilk] " + ChatColor.RED +
                            "You must look the spawner you want to edit");
                    return true;
                }


            }
        }
        return false;
    }
}
