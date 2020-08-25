package org.matejko06.vesek.anarchycore;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

import java.io.IOException;

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