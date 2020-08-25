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
        if(command.getName().equalsIgnoreCase("ac")) {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("help-message")));
            } else if (args[0].equalsIgnoreCase("reload")) {
                Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aReload process started.."));
                Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Unloading &6config.yml&a..."));
                Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully unloaded &6config.yml&a..."));
                plugin.reloadConfig();
                Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading &6config.yml&a..."));
                Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded &6config.yml&a..."));
                plugin.saveConfig();
                AnarchyCORE.command_preprocessing = plugin.getConfig().getBoolean("command-preprocessing");
                Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Reload process ended."));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a has been reloaded!"));
            } else if (args[0].equalsIgnoreCase("version")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6By Matejko06 and Vesek.\n&6&l<----------------------->"));
            } else if (args[0].equalsIgnoreCase("discord")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0 &6&lSUPPORT\n&6By Matejko06 and Vesek.\n&3&lDISCORD: &1https://discord.gg/dh5u3as\n&6&l<----------------------->"));
            } else if (args[0].equalsIgnoreCase("support")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0 &6&lSUPPORT\n&6By Matejko06 and Vesek.\n&3&lDISCORD: &1https://discord.gg/dh5u3as\n&6&l<----------------------->"));
            }
        }
        else if (command.getName().equalsIgnoreCase("acore")) {
                if (args.length < 1) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("help-message")));
                } else if (args[0].equalsIgnoreCase("reload")) {
                    Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aReload process started.."));
                    Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Unloading &6config.yml&a..."));
                    Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully unloaded &6config.yml&a..."));
                    plugin.reloadConfig();
                    Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading &6config.yml&a..."));
                    Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded &6config.yml&a..."));
                    plugin.saveConfig();
                    AnarchyCORE.command_preprocessing = plugin.getConfig().getBoolean("command-preprocessing");
                    Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Reload process ended."));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a has been reloaded!"));
                } else if (args[0].equalsIgnoreCase("version")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6By Matejko06 and Vesek.\n&6&l<----------------------->"));
                } else if (args[0].equalsIgnoreCase("discord")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0 &6&lSUPPORT\n&6By Matejko06 and Vesek.\n&3&lDISCORD: &1https://discord.gg/dh5u3as\n&6&l<----------------------->"));
                } else if (args[0].equalsIgnoreCase("support")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0 &6&lSUPPORT\n&6By Matejko06 and Vesek.\n&3&lDISCORD: &1https://discord.gg/dh5u3as\n&6&l<----------------------->"));
                }
            }
        return false;
    }
}