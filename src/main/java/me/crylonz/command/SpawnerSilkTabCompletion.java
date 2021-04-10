package me.crylonz.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnerSilkTabCompletion implements TabCompleter {

    private List<String> list = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        list.clear();

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("editspawner")) {

                if (args.length == 1) {
                    if (player.hasPermission("spawnersilk.editspawner")) {
                        list.add("spawnrange");
                        list.add("spawncount");
                        list.add("maxNearbyEntities");
                        list.add("requiredPlayerRange");
                        list.add("delay");
                        list.add("maxSpawnDelay");
                        list.add("minSpawnDelay");
                    }
                }
            }

            if (cmd.getName().equalsIgnoreCase("givespawner")) {

                if (args.length == 1) {
                    if (player.hasPermission("spawnersilk.givespawner")) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            list.add(p.getName());
                        }
                    }
                }

                if (args.length == 2) {
                    if (player.hasPermission("spawnersilk.givespawner")) {
                        for (EntityType entityType : EntityType.values()) {
                            list.add(entityType.name().toUpperCase());
                        }
                    }
                }
            }
        }
        return list;
    }
}