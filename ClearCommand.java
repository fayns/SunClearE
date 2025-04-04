package me.fayns.suncleare.commands;

import me.fayns.suncleare.SunClearE;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ClearCommand implements CommandExecutor {

        private final SunClearE plugin = SunClearE.getInstance();

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(color(plugin.getConfig().getString("messages.only-player")));
                return true;
            }

            Player player = (Player) sender;

            if (!player.hasPermission("ggclear.use")) {
                player.sendMessage(color(plugin.getConfig().getString("messages.no-permission")));
                return true;
            }

            if (args.length == 0) {
                player.getInventory().clear();
                player.sendMessage(color(plugin.getConfig().getString("messages.inventory-cleared")));
                return true;
            }

            if (args.length == 2) {
                Material material = Material.getMaterial(args[0].toUpperCase());
                if (material == null) {
                    player.sendMessage(color(plugin.getConfig().getString("messages.invalid-material").replace("%material%", args[0])));
                    return true;
                }

                int amount;
                try {
                    amount = Integer.parseInt(args[1]);
                    if (amount <= 0) {
                        player.sendMessage(color(plugin.getConfig().getString("messages.invalid-amount")));
                        return true;
                    }
                } catch (NumberFormatException e) {
                    player.sendMessage(color(plugin.getConfig().getString("messages.invalid-number")));
                    return true;
                }

                int removed = 0;
                ItemStack[] contents = player.getInventory().getContents();
                for (int i = 0; i < contents.length; i++) {
                    ItemStack item = contents[i];
                    if (item != null && item.getType() == material) {
                        int itemAmount = item.getAmount();
                        if (removed + itemAmount <= amount) {
                            removed += itemAmount;
                            contents[i] = null;
                        } else {
                            int toRemove = amount - removed;
                            item.setAmount(itemAmount - toRemove);
                            removed += toRemove;
                            break;
                        }
                    }
                }

                player.getInventory().setContents(contents);
                player.sendMessage(color(plugin.getConfig().getString("messages.items-cleared")
                        .replace("%amount%", String.valueOf(removed))
                        .replace("%material%", material.name())));
                return true;
            }

            player.sendMessage(color(plugin.getConfig().getString("messages.usage")));
            return true;
        }

        private String color(String msg) {
            return msg.replace("&", "§");
        }
    }
