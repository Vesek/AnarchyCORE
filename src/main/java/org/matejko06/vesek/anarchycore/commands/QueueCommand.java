package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class QueueCommand implements CommandExecutor {
    Plugin plugin;
    public QueueCommand (Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("queue")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("AnarchyCORE.queue.admin")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("admin-message")));
                } else if (sender.hasPermission("AnarchyCORE.queue.priority")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("priority-message")));
                } else if (sender.hasPermission("AnarchyCORE.queue.regular")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("regular-message")));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("invalid-command")));
                }
            }
        }
        return false;
    }
}
