package org.matejko06.vesek.anarchycore;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;

    public class EventsClass implements Listener {
        public int blocksbroke = 0;

        @EventHandler
        public void blockbreak(PlayerJoinEvent event) {
            Player player = event.getPlayer();
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

        org.bukkit.plugin.Plugin plugin;
        public EventsClass (Plugin plugin) {
        }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
                String dm = event.getDeathMessage();
                event.setDeathMessage(null);
                for (Player p : plugin.getServer().getOnlinePlayers()) {
                    p.sendMessage(dm);
                }
    }
}
