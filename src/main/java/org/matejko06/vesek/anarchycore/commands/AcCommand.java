package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

public class AcCommand implements CommandExecutor {
    Plugin plugin;
    public AcCommand (Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("ac")){
            if(args.length < 1){
                sender.sendMessage("Not enough arguments.");
            }
            else if(args.length == 1){
                if(args[0].equalsIgnoreCase("help")){

                }
                else if(args[0].equalsIgnoreCase("reload")){
                    plugin.reloadConfig();
                    plugin.saveConfig();
                    AnarchyCORE.command_preprocessing = plugin.getConfig().getBoolean("command-preprocessing");
                    sender.sendMessage(ChatColor.RED + "AnarchyCORE has been reloaded!");
                }
                else if(args[0].equalsIgnoreCase("version")){

                }
            }
        }
        return false;
    }
}