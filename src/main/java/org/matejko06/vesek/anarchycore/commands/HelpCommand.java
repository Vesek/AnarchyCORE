package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

public class HelpCommand implements CommandExecutor {
    Plugin plugin;
    public HelpCommand (Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("help")) {
            if (sender.hasPermission("AnarchyCORE.help") || sender.isOp()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3<----------------------->\n&6&o/help &3- Shows this.\n&6&o/kill &3- Kills the player.\n&6&o/tps &3- Shows server TPS.\n&6&o/info &3- Shows some info about the server.\n&6&o/priority &3or &6&o/queue &3- Shows players queue priority.\n&3<----------------------->"));
            }
        } else if (sender.hasPermission("AnarchyCORE.tps") || sender.isOp()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3<----------------------->\n&6&o/help &3- Shows this.\n&6&o/kill &3- Kills the player.\n&6&o/tps &3- Shows server TPS.\n&6&o/info &3- Shows some info about the server.\n&6&o/priority &3or &6&o/queue &3- Shows players queue priority.\n&3<----------------------->"));
                    }
        return false;
    }
}