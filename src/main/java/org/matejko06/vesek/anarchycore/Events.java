package org.matejko06.vesek.anarchycore;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class Events implements Listener {

    org.bukkit.plugin.Plugin plugin;
    public Events (Plugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().toLowerCase().startsWith("/tps") && AnarchyCORE.command_preprocessing) {
            event.setCancelled(true);
            event.getPlayer().chat("/anarchycore:tps");
        }
        if (event.getMessage().toLowerCase().startsWith("/help") && AnarchyCORE.command_preprocessing) {
            event.setCancelled(true);
            event.getPlayer().chat("/anarchycore:help");
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        String dm = event.getDeathMessage();
        event.setDeathMessage(null);
        for(Player p : plugin.getServer().getOnlinePlayers()){
            p.sendMessage(dm);
        }
    }
}
