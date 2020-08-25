package org.matejko06.vesek.anarchycore;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;


public class Events implements Listener {

    org.bukkit.plugin.Plugin plugin;

    public Events(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().toLowerCase().startsWith("/tps") && AnarchyCORE.command_preprocessing) {
            event.setCancelled(true);
            event.getPlayer().chat("/anarchycore:tps");
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        String s = e.getMessage();
        if(s.charAt(0) == '>'){
            e.getPlayer().sendMessage(s);
            e.setMessage(ChatColor.GREEN + e.getMessage().substring(1,s.length()));
        }
    }
}