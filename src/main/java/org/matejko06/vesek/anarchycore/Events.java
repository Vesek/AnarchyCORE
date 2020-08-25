package org.matejko06.vesek.anarchycore;

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
        if (event.getMessage().toLowerCase().startsWith("/info") && AnarchyCORE.command_preprocessing) {
            event.setCancelled(true);
            event.getPlayer().chat("/anarchycore:info");
        }
    }

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
        String message = e.getMessage().replaceFirst(">", "§a>");
        e.setMessage(message);
    }
}
