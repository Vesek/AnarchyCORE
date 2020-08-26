package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TPSCommand implements CommandExecutor {
    AnarchyCORE ac;
    public TPSCommand(AnarchyCORE ac) {
        this.ac = ac;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tps")) {
            if (sender.hasPermission("AnarchyCORE.tps") || sender.isOp()) {
                StringBuilder sb = new StringBuilder();
                double[] TPS = ac.getServer().getTPS();
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);
                if (TPS[0] >= ac.getConfig().getDouble("tps-green")) {
                    sb.append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("tps-green-color"))).append(df.format(TPS[0]));
                } else if (TPS[0] >= ac.getConfig().getDouble("tps-yellow")) {
                    sb.append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("tps-yellow-color"))).append(df.format(TPS[0]));
                } else if (TPS[0] >= 0.00) {
                    sb.append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("tps-red-color"))).append(df.format(TPS[0]));
                }
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', sb.toString()));
            }
        }
        return false;
    }
}