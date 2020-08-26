package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

public class KillCommand implements CommandExecutor {
    AnarchyCORE ac;
    public KillCommand(AnarchyCORE ac) {
        this.ac = ac;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kill")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 0) {
                    if (p.hasPermission("AnarchyCORE.kill") || p.isOp()) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getCfgm().deathmessagescfg.getString("Suicide-Message")));
                        p.setHealth(0.00);
                    }
                }
                if (args.length >= 1) {
                    if (p.hasPermission("AnarchyCORE.killSomeone") || p.isOp()) {
                        for (String s : args) {
                            Player victim = ac.getServer().getPlayer(s);
                            if (victim != null) {
                                victim.sendMessage(ChatColor.translateAlternateColorCodes('&', ac.getCfgm().deathmessagescfg.getString("Kill-Someone-Message")));
                                victim.setHealth(0.00);
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cOnly players can use this command!"));
            }
        }
        return false;
    }
}