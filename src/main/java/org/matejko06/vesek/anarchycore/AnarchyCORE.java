package org.matejko06.vesek.anarchycore;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.matejko06.vesek.anarchycore.commands.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class AnarchyCORE extends JavaPlugin implements Listener {

    public static boolean command_preprocessing = false;

    VersionCommand vc = new VersionCommand(this);
    TPSCommand tc = new TPSCommand(this);
    QueueCommand qc = new QueueCommand(this);
    KillCommand kc = new KillCommand(this);
    InfoCommand ic = new InfoCommand(this);
    HelpCommand hc = new HelpCommand(this);
    ReloadCommand rc = new ReloadCommand(this);

    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(text);
    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(this, this);
        command_preprocessing = getConfig().getBoolean("command-preprocessing");
        //getCommand("ac_version").setExecutor(vc);
        getCommand("tps").setExecutor(tc);
        getCommand("queue").setExecutor(qc);
        getCommand("kill").setExecutor(kc);
        getCommand("info").setExecutor(ic);
        getCommand("acreload").setExecutor(rc);
        //getCommand("ac_help").setExecutor(hc);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a&l turned on!"));
    }

    @Override
    public void onDisable() {
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&c&l turned off!"));

    }

    @EventHandler
    public void onCommandPreProcess(PlayerCommandPreprocessEvent event){
        if(event.getMessage().toLowerCase().startsWith("/tps") && command_preprocessing){
            event.setCancelled(true);
            event.getPlayer().chat("/anarchycore:tps");
            event.getPlayer().chat("/AnarchyCORE:tps");
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        String dm = event.getDeathMessage();
        event.setDeathMessage(null);
        for(Player p : getServer().getOnlinePlayers()){
            p.sendMessage(dm);
        }
    }
}