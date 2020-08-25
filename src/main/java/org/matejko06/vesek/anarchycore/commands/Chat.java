package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

public class Chat implements Listener {
    public AnarchyCORE plugin;

    public Chat(AnarchyCORE plugin) {
        this.plugin = plugin;
    }

    public String Colors(String msg, Player player) {
        if ((this.plugin.getConfig().getBoolean("chat-color-symbol") || player.hasPermission("AnarchyCORE.colorsymbol")) &&
                !this.plugin.getConfig().getString("chat-color-symbol").equals("!#") && msg.startsWith(">"))
            msg = ChatColor.GREEN + msg;
        return msg;
    }
}