package me.fayns.suncleare.commands;

import me.fayns.suncleare.SunClearE;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class Reload implements CommandExecutor {

        private final SunClearE plugin = SunClearE.getInstance();

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("ggclear.reload")) {
                    sender.sendMessage(color(plugin.getConfig().getString("messages.no-permission")));
                    return true;
                }

                plugin.reload();
                sender.sendMessage(color(plugin.getConfig().getString("messages.reloaded")));
                return true;
            }

            sender.sendMessage(color(plugin.getConfig().getString("messages.reload-usage")));
            return true;
        }

        private String color(String msg) {
            return msg.replace("&", "§");
        }
    }

