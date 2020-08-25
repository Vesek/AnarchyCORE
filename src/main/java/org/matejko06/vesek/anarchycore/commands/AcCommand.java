package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.Bukkit;
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
        if (command.getName().equalsIgnoreCase("ac")) {
                if (args.length < 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                        if (sender.hasPermission("AnarchyCORE.reload")) {
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aReload process started.."));
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Unloading &6config.yml&a..."));
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully unloaded &6config.yml&a..."));
                            plugin.reloadConfig();
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading &6config.yml&a..."));
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded &6config.yml&a..."));
                            plugin.saveConfig();
                            AnarchyCORE.command_preprocessing = plugin.getConfig().getBoolean("command-preprocessing");
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Reload process ended."));
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a has been reloaded!"));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a has been reloaded!"));
                        }
                    } else if (args[0].equalsIgnoreCase("version")) {
                        if (sender.hasPermission("AnarchyCORE.version")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6By Matejko06 and Vesek.\n&6&l<----------------------->"));
                        }
                    } else if (args[0].equalsIgnoreCase("discord")) {
                        if (sender.hasPermission("AnarchyCORE.discord")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0 &6&lSUPPORT\n&6By Matejko06 and Vesek.\n&3&lDISCORD: &1https://discord.gg/dh5u3as\n&6&l<----------------------->"));
                        }
                    } else if (args[0].equalsIgnoreCase("support")) {
                        if (sender.hasPermission("AnarchyCORE.discord")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0 &6&lSUPPORT\n&6By Matejko06 and Vesek.\n&3&lDISCORD: &1https://discord.gg/dh5u3as\n&6&l<----------------------->"));
                        }
                    } else if (args[0].equalsIgnoreCase("adminhelp")) {
                        if (sender.hasPermission("AnarchyCORE.adminhelp")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6by Matejko06 & Vesek\n \n&6&o/help &3- Shows this.\n&6&o/kill &3- Kills the player.\n&6&o/tps &3- Shows server TPS.\n&6&o/info &3- Shows some info about the server.\n&6&o/priority &3or &3- Shows players queue priority.\n&6&l/ac adminhelp &3- Shows this.\n&6&l/ac version &3- Shows plugin version.\n&6&l/ac support &3- Sends you support discord link.&6&l/ac reload &3- Reloads the plugin.\n&6&l<----------------------->"));
                        }
                    }
                }
        } else if (command.getName().equalsIgnoreCase("acore")) {
                if (args.length < 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        if (sender.hasPermission("AnarchyCORE.reload")) {
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aReload process started.."));
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Unloading &6config.yml&a..."));
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully unloaded &6config.yml&a..."));
                            plugin.reloadConfig();
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading &6config.yml&a..."));
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded &6config.yml&a..."));
                            plugin.saveConfig();
                            AnarchyCORE.command_preprocessing = plugin.getConfig().getBoolean("command-preprocessing");
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Reload process ended."));
                            Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a has been reloaded!"));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a has been reloaded!"));
                        }
                    } else if (args[0].equalsIgnoreCase("version")) {
                        if (sender.hasPermission("AnarchyCORE.version")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6By Matejko06 and Vesek.\n&6&l<----------------------->"));
                        }
                    } else if (args[0].equalsIgnoreCase("discord")) {
                        if (sender.hasPermission("AnarchyCORE.discord")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0 &6&lSUPPORT\n&6By Matejko06 and Vesek.\n&3&lDISCORD: &1https://discord.gg/dh5u3as\n&6&l<----------------------->"));
                        } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("invalid-message")));
                    } else if (args[0].equalsIgnoreCase("support")) {
                        if (sender.hasPermission("AnarchyCORE.discord")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0 &6&lSUPPORT\n&6By Matejko06 and Vesek.\n&3&lDISCORD: &1https://discord.gg/dh5u3as\n&6&l<----------------------->"));
                        } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("invalid-message")));
                    } else if (args[0].equalsIgnoreCase("adminhelp")) {
                        if (sender.hasPermission("AnarchyCORE.adminhelp")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6by Matejko06 & Vesek\n \n&6&o/help &3- Shows this.\n&6&o/kill &3- Kills the player.\n&6&o/tps &3- Shows server TPS.\n&6&o/info &3- Shows some info about the server.\n&6&o/priority &3or &3- Shows players queue priority.\n&6&l/ac adminhelp &3- Shows this.\n&6&l/ac version &3- Shows plugin version.\n&6&l/ac support &3- Sends you support discord link.&6&l/ac reload &3- Reloads the plugin.\n&6&l<----------------------->"));
                        } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("invalid-message")));
                    }
                }
            }
        return false;
    }
}