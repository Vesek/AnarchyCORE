package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class TPSCommand implements CommandExecutor {
    Plugin plugin;
    public TPSCommand (Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tps")) {
            if (sender.hasPermission("AnarchyCORE.tps") || sender.isOp()) {
                StringBuilder sb = new StringBuilder();
                double[] TPS = plugin.getServer().getTPS();
                if (TPS[0] >= plugin.getConfig().getDouble("tps-green")) {
                    sb.append(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tps-green-color"))).append(String.format("%.2f", TPS[0]));
                } else if (TPS[0] >= plugin.getConfig().getDouble("tps-yellow")) {
                    sb.append(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tps-yellow-color"))).append(String.format("%.2f", TPS[0]));
                } else if (TPS[0] >= 0.00) {
                    sb.append(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tps-red-color"))).append(String.format("%.2f", TPS[0]));
                }
                sender.sendMessage(sb.toString());
            }
    }
        return false;
    }
}
