package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

public class HelpCommand implements CommandExecutor {
    AnarchyCORE ac;

    public HelpCommand(AnarchyCORE ac) {
        this.ac = ac;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("help")) {
            if (sender.hasPermission("AnarchyCORE.help"))
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getCfgm().messagescfg.getString("Help-Message")));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getCfgm().messagescfg.getString("Help-Message")));
    }
        return false;
    }
}