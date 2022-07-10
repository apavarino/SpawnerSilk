package me.crylonz.command;

import me.crylonz.SpawnerAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.crylonz.SpawnerSilk.log;
import static me.crylonz.SpawnerSilk.playersUUID;

public class GiveSpawnerCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // if command is givespawner
        if (cmd.getName().equalsIgnoreCase("givespawner")) {

            Player player = null;
            if ((sender instanceof Player))
                player = (Player) sender;

            if ((player != null && player.hasPermission("spawnersilk.givespawner")) || (!(sender instanceof Player))) {

                String STR_INVALID_FORMAT = "[SpawnerSilk] BAD ARGUMENTS : Usage givespawner <Player> <SpawnerType> [Amount]";
                if (args.length >= 2 && args.length < 4) {
                    Player destinator = null;

                    // get destinator of the command
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getUniqueId().toString().equalsIgnoreCase(getUuid(args[0]))) {
                            destinator = p;
                            break;
                        }
                    }

                    if (destinator != null) {
                        int amount = 1;
                        if (args.length >= 3) {
                            if (args[2].matches("-?(0|[1-9]\\d*)"))
                                amount = Integer.parseInt(args[2]);
                            else {
                                if (player != null)
                                    player.sendMessage(ChatColor.RED + STR_INVALID_FORMAT);
                                else
                                    log.info(STR_INVALID_FORMAT);
                                return true;
                            }
                        }

                        ItemStack is = SpawnerAPI.stringToCustomItemStack(args[1], amount);

                        // if the item is valid
                        if (is.getItemMeta() != null && SpawnerAPI.getEntityType(is) != EntityType.UNKNOWN) {
                            destinator.getInventory().addItem(is);
                            String STR_CMD_SUCCESSFULL = "[SpawnerSilk] Command done successful !";
                            if (player != null)
                                player.sendMessage(ChatColor.GREEN + STR_CMD_SUCCESSFULL);
                            else
                                log.info(STR_CMD_SUCCESSFULL);
                        }
                        // item not valid
                        else {
                            String STR_BAD_FORMAT = "[SpawnerSilk] Unknown type of spawner";
                            if (player != null)
                                player.sendMessage(ChatColor.RED + STR_BAD_FORMAT);
                            else
                                log.info(STR_BAD_FORMAT);
                        }


                        // destinator not valid
                    } else {
                        String STR_INVALID_PLAYER = "[SpawnerSilk] Player does not exist";
                        if (player != null)
                            player.sendMessage(ChatColor.RED + STR_INVALID_PLAYER);
                        else
                            log.info(STR_INVALID_PLAYER);
                    }

                    // not good argument structure
                } else if (player != null)
                    player.sendMessage(ChatColor.RED + STR_INVALID_FORMAT);
                else
                    log.info(STR_INVALID_FORMAT);
            }
        }
        return true;
    }

    private String getUuid(String name) {

        final String[] uuid = new String[1];
        uuid[0] = "error";
        playersUUID.forEach((k, v) -> {
            if (k.equals(name)) {
                uuid[0] = v;
            }
        });
        return uuid[0];
    }
}