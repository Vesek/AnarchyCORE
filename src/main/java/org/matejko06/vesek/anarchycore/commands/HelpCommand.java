package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class HelpCommand implements CommandExecutor {
    Plugin plugin;
    public HelpCommand (Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("help")) {
            if (sender.hasPermission("AnarchyCORE.help"))
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("help-message")));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("help-message")));
    }
        return false;
    }
}
