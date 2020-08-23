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
                if (sender.hasPermission("AnarchyCORE.adminhelp") || sender.isOp()) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE&a&l V1.3.0 &6&lADMINHELP\n\n&6&o/help &a- Shows server help.\n&6&o/adminhelp &a- Shows this.\n&6&o/kill &a- Kills the player.\n&6&o/kill <player> &a- Kills someone.\n&6&o/tps &a- Shows server TPS.\n&6&o/info &a- Shows some info about the server.\n&6&o/priority &aor &6&o/queue &a- Shows players queue priority.\n&6&l<----------------------->"));
                } else if (sender.hasPermission("AnarchyCORE.version") || sender.isOp()) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE &a&lV1.3.0\n&6By Matejko06 and Vesek"));
                }
            } else if (args.length < 1) {
                if (args[0].equalsIgnoreCase("adminhelp")) {
                    if (sender.hasPermission("AnarchyCORE.adminhelp") || sender.isOp()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l<----------------------->\n&6&lAnarchyCORE&a&l V1.3.0 &6&lADMINHELP\n\n&6&o/help &a- Shows server help.\n&6&o/adminhelp &a- Shows this.\n&6&o/kill &a- Kills the player.\n&6&o/kill <player> &a- Kills someone.\n&6&o/tps &a- Shows server TPS.\n&6&o/info &a- Shows some info about the server.\n&6&o/priority &aor &6&o/queue &a- Shows players queue priority.\n&6&l<----------------------->"));
                    } else if (sender.hasPermission("AnarchyCORE.version") || sender.isOp()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE &a&lV1.3.0\n&6By Matejko06 and Vesek"));
                    }
                }
            }
        return false;
    }
}