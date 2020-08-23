package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

public class AcoreCommand implements CommandExecutor {
    Plugin plugin;

    public AcoreCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("acore")) {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNot enough arguments."));
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {

                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("AnarchyCORE.reload") || sender.isOp()) {
                        plugin.reloadConfig();
                        plugin.saveConfig();
                        AnarchyCORE.command_preprocessing = plugin.getConfig().getBoolean("command-preprocessing");
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a has been reloaded!"));
                    } else if (args[0].equalsIgnoreCase("acreload")) {
                        if (sender.hasPermission("AnarchyCORE.reload") || sender.isOp()) {
                            plugin.reloadConfig();
                            plugin.saveConfig();
                            AnarchyCORE.command_preprocessing = plugin.getConfig().getBoolean("command-preprocessing");
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a has been reloaded!"));
                        } else if (args[0].equalsIgnoreCase("acorereload")) {
                            if (sender.hasPermission("AnarchyCORE.reload") || sender.isOp()) {
                                plugin.reloadConfig();
                                plugin.saveConfig();
                                AnarchyCORE.command_preprocessing = plugin.getConfig().getBoolean("command-preprocessing");
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a has been reloaded!"));
                            }
                            else if (sender.hasPermission("AnarchyCORE.reload") || sender.isOp()) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE &a&lV1.3.0\n&6By Matejko06 and Vesek"));
                            }
                        }
                    }
                }
            }
        }
                return false;
    }
}