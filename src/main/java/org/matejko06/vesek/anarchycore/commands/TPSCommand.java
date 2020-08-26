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
            if (command.getName().equalsIgnoreCase("tps")) {
                if (sender.hasPermission("FinishHim.tps") || sender.isOp()) {
                    StringBuilder sb = new StringBuilder();
                    double[] TPS = ac.getServer().getTPS();
                    if (TPS[0] >= ac.getConfig().getDouble("tps-green")) {
                        sb.append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("tps-green-color"))).append(String.format("%.2f", new Object[] { Double.valueOf(TPS[0]) }));
                    } else if (TPS[0] >= ac.getConfig().getDouble("tps-yellow")) {
                        sb.append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("tps-yellow-color"))).append(String.format("%.2f", new Object[] { Double.valueOf(TPS[0]) }));
                    } else if (TPS[0] >= 0.0D) {
                        sb.append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("tps-red-color"))).append(String.format("%.2f", new Object[] { Double.valueOf(TPS[0]) }));
                    }
                    sender.sendMessage(sb.toString());
                }
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("invalid-command-message")));
            }
        }
        return false;
    }
}