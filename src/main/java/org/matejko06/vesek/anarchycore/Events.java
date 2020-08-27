package org.matejko06.vesek.anarchycore;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;


public class Events implements Listener {

    AnarchyCORE ac;

    public Events(AnarchyCORE ac) {
            this.ac = ac;
        }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player JoiningPlayer = e.getPlayer();
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', ac.getCfgm().messagescfg.getString("Player-Join-Message").replace("%playername%", JoiningPlayer.getDisplayName())));
/*        new UpdateChecker(this, 82999).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
            } else {
                if (p.hasPermission("AnarchyCORE.*") || p.isOp()) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThere is a new update available!"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &cCurrent version: &6" + this.getDescription().getVersion() + " &7&l| &aNew version: &6" + version));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aDownload it at: &3https://www.spigotmc.org/resources/anarchycore.82999"));
                }
            }
        });
*/    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player LeavingPlayer = e.getPlayer();
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', ac.getCfgm().messagescfg.getString("Player-Quit-Message").replace("%playername%", LeavingPlayer.getDisplayName())));
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
        if(s.charAt(0) == ac.getConfig().getString("Green-Chat-Symbol").charAt(0)){
            e.setMessage(ChatColor.GREEN + ac.getConfig().getString("Green-Chat-Symbol") + e.getMessage().substring(1,s.length()));
        }
    }
}