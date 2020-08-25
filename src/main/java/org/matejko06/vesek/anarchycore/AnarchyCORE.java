package org.matejko06.vesek.anarchycore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.matejko06.vesek.anarchycore.commands.*;

import java.util.ArrayList;
import java.util.List;

public final class AnarchyCORE extends JavaPlugin implements Listener {

    public static boolean command_preprocessing = false;

    TPSCommand tc = new TPSCommand(this);
    QueueCommand qc = new QueueCommand(this);
    KillCommand kc = new KillCommand(this);
    InfoCommand ic = new InfoCommand(this);
    HelpCommand hc = new HelpCommand(this);
    AcCommand ac = new AcCommand(this);
    Events events = new Events(this);


    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(text);
    }
    private ConfigManager cfgm;

    @Override
    public void onEnable() {
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a is turning on..."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading &6config.yml&a."));
        getConfig().options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(events, this);
        command_preprocessing = getConfig().getBoolean("command-preprocessing");
        loadConfigManager();
        getServer().getPluginManager().registerEvents(new Events(this), this);
        loadConfig();
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded &6config.yml&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading command &6tps&a."));
        getCommand("tps").setExecutor(tc);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded command &6tps&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading command &6priority&a."));
        getCommand("priority").setExecutor(qc);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded command &6priority&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading command &6kill&a."));
        getCommand("kill").setExecutor(kc);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded command &6kill&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading command &6info&a."));
        getCommand("info").setExecutor(ic);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded command &6info&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading command &6help&a."));
        getCommand("help").setExecutor(hc);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded command &6help&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading command prefix &6acore&a."));
        getCommand("acore").setExecutor(ac);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded command prefix &6acore&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading command prefix &6ac&a."));
        getCommand("ac").setExecutor(ac);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded command prefix &6ac&a."));
        Bukkit.getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(this, this);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a turned on!"));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&3" + p.getDisplayName() + "&7 " + getConfig().getString("join-message")));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&3" + p.getDisplayName() + "&7 " + getConfig().getString("leave-message")));
    }

    @Override
    public void onDisable() {
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a is turning off..."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Saving &6config.yml&a."));
        saveConfig();
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully saved &6config.yml&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Unloading &6config.yml&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully unloaded &6config.yml&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Unoading command &6tps&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully unloaded command &6tps&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Unloading command &6priority&a."));
        getCommand("priority").setExecutor(qc);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully unloaded command &6priority&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Unlooading command &6kill&a."));
        getCommand("kill").setExecutor(kc);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully unloaded command &6kill&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Unlooading command &6info&a."));
        getCommand("info").setExecutor(ic);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully unloaded command &6info&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Unlooading command &6help&a."));
        getCommand("help").setExecutor(hc);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully unloaded command &6help&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Unlooading command prefix &6acore&a."));
        getCommand("acore").setExecutor(ac);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully unloaded command prefix &6acore&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Unlooading command prefix &6ac&a."));
        getCommand("ac").setExecutor(ac);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully unloaded command prefix &6ac&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&c turned off!"));
    }

    public void loadConfigManager() {
        cfgm = new ConfigManager();
        cfgm.setup();
//        cfgm.saveMessages();
//        cfgm.reloadMessages();

    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

//    public void loadMessages() {
//        getMessages().options().copyDefaults(true);
//        saveMessages();
//    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(sender instanceof Player){
            if(command.getName().equalsIgnoreCase("kill") || command.getName().equalsIgnoreCase("suicide")){
                if(sender.hasPermission("AnarchyCORE.killSomeone") || sender.isOp()){
                    List<String> tab = new ArrayList<>();
                    for(Player p : getServer().getOnlinePlayers()){
                        tab.add(p.getDisplayName());
                    }
                    return tab;
                }
            }
            else if(command.getName().equalsIgnoreCase("acore")){
                List<String> tab = new ArrayList<>();
                tab.add("reload");
                tab.add("help");
                tab.add("version");
                tab.add("discord");
                tab.add("support");
                return tab;
            }
            else if(command.getName().equalsIgnoreCase("ac")){
                List<String> tab = new ArrayList<>();
                tab.add("reload");
                tab.add("help");
                tab.add("version");
                tab.add("discord");
                return tab;
            }
        }
        return null;
    }
}