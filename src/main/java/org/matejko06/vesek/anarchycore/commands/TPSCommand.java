package org.matejko06.vesek.anarchycore.commands;

public class TPSCommand {
}
//        } else if (command.getName().equalsIgnoreCase("tps")) {
//                if (sender.hasPermission("ValueSkinsCORE.tps") || sender.isOp()) {
//                StringBuilder sb = new StringBuilder();
//                double[] TPS = getServer().getTPS();
//                if (TPS[0] >= getConfig().getDouble("tps-green")) {
//                sb.append(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tps-green-color"))).append(String.format("%.2f", TPS[0]));
//                } else if (TPS[0] >= getConfig().getDouble("tps-yellow")) {
//                sb.append(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tps-yellow-color"))).append(String.format("%.2f", TPS[0]));
//                } else if (TPS[0] >= 0.00) {
//                sb.append(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tps-red-color"))).append(String.format("%.2f", TPS[0]));
//                }
//                sender.sendMessage(sb.toString());
//                }