package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class KillCommand implements CommandExecutor {
    Plugin plugin;
    public KillCommand (Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kill")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 0) {
                    if (p.hasPermission("AnarchyCORE.kill") || p.isOp()) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("kill-message")));
                        p.setHealth(0.00);
                    }
                }
                if (args.length >= 1) {
                    if (p.hasPermission("AnarchyCORE.killSomeone") || p.isOp()) {
                        for (String s : args) {
                            Player victim = plugin.getServer().getPlayer(s);
                            if (victim != null) {
                                victim.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("killsomeone-message")));
                                victim.setHealth(0.00);
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage("Only players can use this command!");
            }
        }
        return false;
    }
}
