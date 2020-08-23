package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

public class ReloadCommand implements CommandExecutor {
    Plugin plugin;
    public ReloadCommand (Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("reloadac")){
            plugin.reloadConfig();
            plugin.saveConfig();
            AnarchyCORE.command_preprocessing = plugin.getConfig().getBoolean("command-preprocessing");
            sender.sendMessage(ChatColor.RED + "AnarchyCORE has been reloaded!");
        }
        return false;
    }
}
