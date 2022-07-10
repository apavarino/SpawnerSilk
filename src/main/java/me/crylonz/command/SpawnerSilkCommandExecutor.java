package me.crylonz.command;

import me.crylonz.SpawnerSilk;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.crylonz.SpawnerSilk.log;

public class SpawnerSilkCommandExecutor implements CommandExecutor {

    private final SpawnerSilk plugin;

    public SpawnerSilkCommandExecutor(SpawnerSilk plugin) {
        this.plugin = plugin;
    }

    private final String UNKNOWN_COMMAND = "Unknown command";
    private final String PLUGIN_RELOAD = "Plugin reloaded successfully !";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("sps")) {

            Player player = null;
            if ((sender instanceof Player))
                player = (Player) sender;


            if (args.length == 1) {
                switch (args[0].toLowerCase()) {
                    case "reload":
                        if (player == null || player.hasPermission("spawnerSilk.reload")) {
                            reloadPlugin();
                            displayMessage(player, PLUGIN_RELOAD, false);
                        }
                        break;
                    default:
                        displayMessage(player, UNKNOWN_COMMAND, true);
                        break;
                }
            } else {
                displayMessage(player, UNKNOWN_COMMAND, true);
            }
        }
        return true;
    }

    private void reloadPlugin() {
        plugin.reloadConfig();
        plugin.registerConfig();
    }

    private void displayMessage(Player player, String message, boolean isError) {
        String prefixMessage;

        if (isError) {
            prefixMessage = ChatColor.RED.toString();
        } else {
            prefixMessage = ChatColor.GREEN.toString();
        }


        if (player == null) {
            log.info("[SpawnerSilk] " + message);
        } else {
            player.sendMessage(prefixMessage + "[SpawnerSilk] " + message);
        }
    }
}

