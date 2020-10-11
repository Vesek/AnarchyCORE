package org.matejko06.vesek.anarchycore.commands;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

import java.io.File;

public class AnarchyCORECommand implements CommandExecutor {

    AnarchyCORE ac;
    public AnarchyCORECommand (AnarchyCORE ac){
        this.ac = ac;
    }

    public FileConfiguration messagescfg;
    public File messagesfile;
    public FileConfiguration deathmessagescfg;
    public File deathmessagesfile;
    public FileConfiguration tabconfigcfg;
    public File tabconfigfile;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("anarchycore")) {
            if(args.length == 0){
                if (sender.hasPermission("AnarchyCORE.adminhelp") || sender.isOp()) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6by Matejko06 & Vesek\n \n&6&o/help &3- Shows public help.\n&6&l/ac adminhelp &3- Shows this.\n&6&l/ac version &3- Shows plugin version.\n&6&l/ac support &3- Sends you support discord link.\n&6&l/ac reload &3- Reloads the plugin.\n&6&l<----------------------->"));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.messagescfg.getString("Invalid-Command-Message")));
                }
            }
            else {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("AnarchyCORE.reload") || sender.isOp()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Starting reload process..."));
                        ac.reloadConfig();
                        ac.saveConfig();
                        ac.getDataFolder();
                        ac.deathmessagescfg.options().copyDefaults(true);
                        ac.deathmessagescfg.save(ac.deathmessagesfile);
                        ac.messagescfg.load(ac.messagesfile);
                        ac.messagescfg.options().copyDefaults(true);
                        ac.messagescfg.save(ac.messagesfile);
                        ac.messagescfg.load(ac.messagesfile);
                        ac.tabconfigcfg.options().copyDefaults(true);
                        ac.tabconfigcfg.save(ac.tabconfigfile);
                        ac.tabconfigcfg.load(ac.tabconfigfile);
                        AnarchyCORE.command_preprocessing = ac.getConfig().getBoolean("Command-Preprocessing");
                        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded all &6configs&a."));
                        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Plugin has been reloaded!"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Plugin has been reloaded!"));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.messagescfg.getString("Invalid-Command-Message")));
                    }
                } else if (args[0].equalsIgnoreCase("version")) {
                    if (sender.hasPermission("AnarchyCORE.version") || sender.isOp()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6By Matejko06 and Vesek.\n&6&l<----------------------->"));
                    } else {
                        if (sender.hasPermission("AnarchyCORE.adminhelp") || sender.isOp()) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6by Matejko06 & Vesek\n \n&6&o/help &3- Shows public help.\n&6&l/ac adminhelp &3- Shows this.\n&6&l/ac version &3- Shows plugin version.\n&6&l/ac support &3- Sends you support discord link.\n&6&l/ac reload &3- Reloads the plugin.\n&6&l<----------------------->"));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.messagescfg.getString("Invalid-Command-Message")));
                        }
                    }} else if (args[0].equalsIgnoreCase("discord")) {
                    if (sender.hasPermission("AnarchyCORE.discord") || sender.isOp()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0 &6&lSUPPORT\n&6By Matejko06 and Vesek.\n&3&lDISCORD: &1https://discord.gg/dh5u3as\n&6&l<----------------------->"));
                    } else {
                        if (sender.hasPermission("AnarchyCORE.adminhelp") || sender.isOp()) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6by Matejko06 & Vesek\n \n&6&o/help &3- Shows server help.\n&6&l/ac adminhelp &3- Shows this.\n&6&l/ac version &3- Shows plugin version.\n&6&l/ac support &3- Sends you support discord link.\n&6&ln/ac reload &3- Reloads the plugin.\n&6&l<----------------------->"));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.messagescfg.getString("Invalid-Command-Message")));
                        }
                    }} else if (args[0].equalsIgnoreCase("support")) {
                    if (sender.hasPermission("AnarchyCORE.discord") || sender.isOp()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0 &6&lSUPPORT\n&6By Matejko06 and Vesek.\n&3&lDISCORD: &1https://discord.gg/dh5u3as\n&6&l<----------------------->"));
                    } else {
                        if (sender.hasPermission("AnarchyCORE.adminhelp") || sender.isOp()) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6by Matejko06 & Vesek\n \n&6&o/help &3- Shows server help.\n&6&l/ac adminhelp &3- Shows this.\n&6&l/ac version &3- Shows plugin version.\n&6&l/ac support &3- Sends you support discord link.\n&6&ln/ac reload &3- Reloads the plugin.\n&6&l<----------------------->"));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.messagescfg.getString("Invalid-Command-Message")));
                        }
                    }} else if (args[0].equalsIgnoreCase("adminhelp")) {
                    if (sender.hasPermission("AnarchyCORE.adminhelp") || sender.isOp()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6by Matejko06 & Vesek\n \n&6&o/help &3- Shows server help.\n&6&l/ac adminhelp &3- Shows this.\n&6&l/ac version &3- Shows plugin version.\n&6&l/ac support &3- Sends you support discord link.\n&6&ln/ac reload &3- Reloads the plugin.\n&6&l<----------------------->"));
                    } else {
                        if (sender.hasPermission("AnarchyCORE.adminhelp") || sender.isOp()) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6by Matejko06 & Vesek\n \n&6&o/help &3- Shows server help.\n&6&l/ac adminhelp &3- Shows this.\n&6&l/ac version &3- Shows plugin version.\n&6&l/ac support &3- Sends you support discord link.\n&6&ln/ac reload &3- Reloads the plugin.\n&6&l<----------------------->"));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.messagescfg.getString("Invalid-Command-Message")));
                        }
                    }} else {
                    if (sender.hasPermission("AnarchyCORE.adminhelp") || sender.isOp()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE &a&lV1.3.0\n&6by Matejko06 & Vesek\n \n&6&o/help &3- Shows server help.\n&6&l/ac adminhelp &3- Shows this.\n&6&l/ac version &3- Shows plugin version.\n&6&l/ac support &3- Sends you support discord link.\n&6&ln/ac reload &3- Reloads the plugin.\n&6&l<----------------------->"));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.messagescfg.getString("Invalid-Command-Message")));
                    }
                }
            }
        }
        return false;
    }
}