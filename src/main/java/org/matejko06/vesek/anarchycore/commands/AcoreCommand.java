package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

public class AcoreCommand implements CommandExecutor {
    Plugin plugin;
    FileConfiguration messages = AnarchyCORE.getMessageConfig();
    public AcoreCommand (Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("acore")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(args.length < 1){
                    sender.sendMessage("Not enough arguments.");
                }
                else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("help")){
                        if(p.hasPermission("AnarchyCORE.adminhelp") || p.isOp()){
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("adminhelp-message")));
                        }
                        else if(p.hasPermission("AnarchyCORE.help")){
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("help-message")));
                        }
                    }
                    else if(args[0].equalsIgnoreCase("reload")){
                        if(p.hasPermission("AnarchyCORE.reload") || p.isOp()){
                            plugin.reloadConfig();
                            plugin.saveConfig();
                            AnarchyCORE.command_preprocessing = plugin.getConfig().getBoolean("command-preprocessing");
                            sender.sendMessage(ChatColor.RED + "AnarchyCORE has been reloaded!");
                        }
                    }
                    else if(args[0].equalsIgnoreCase("version")){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE &a&lV1.3.0\n&6By Matejko06 and Vesek"));
                    }
                }
            }
        }
        return false;
    }
}