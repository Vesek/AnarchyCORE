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
                if (TPS[0] >= ac.getConfig().getDouble("TPS-Green")) {
                    sb.append(ChatColor.translateAlternateColorCodes('&', ac.messagescfg.getString("TPS-Message"))).append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("TPS-Green-Color"))).append(df.format(TPS[0]));
                } else if (TPS[0] >= ac.getConfig().getDouble("TPS-Yellow")) {
                    sb.append(ChatColor.translateAlternateColorCodes('&', ac.messagescfg.getString("TPS-Message"))).append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("TPS-Yellow-Color"))).append(df.format(TPS[0]));
                } else if (TPS[0] >= 0.00) {
                    sb.append(ChatColor.translateAlternateColorCodes('&', ac.messagescfg.getString("TPS-Message"))).append(ChatColor.translateAlternateColorCodes('&', ac.getConfig().getString("TPS-Red-Color"))).append(df.format(TPS[0]));
                }
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', sb.toString()));
            }
        }
        return false;
    }
}