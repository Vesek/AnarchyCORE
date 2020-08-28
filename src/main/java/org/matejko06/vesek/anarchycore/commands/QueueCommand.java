package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

public class
QueueCommand implements CommandExecutor {
    AnarchyCORE ac;
    public QueueCommand(AnarchyCORE ac) {
        this.ac = ac;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("priority")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("AnarchyCOREQueue.admin")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("admin-message")));
                } else if (sender.hasPermission("AnarchyCOREQueue.priority")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("priority-message")));
                } else if (sender.hasPermission("AnarchyCOREQueue.veteran")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("veteran-message")));
                } else if (sender.hasPermission("AnarchyCOREQueue.regular")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("regular-message")));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("invalid-command-message")));
                }
            }
            else if (command.getName().equalsIgnoreCase("queuepriority")) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("AnarchyCOREQueue.admin")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("admin-message")));
                    } else if (sender.hasPermission("AnarchyCOREQueue.priority")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("priority-message")));
                    } else if (sender.hasPermission("AnarchyCOREQueue.veteran")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("priority-message")));
                    } else if (sender.hasPermission("AnarchyCOREQueue.regular")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("regular-message")));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("invalid-command-message")));
                    }
                }
            }
        }
        return false;
    }
}
