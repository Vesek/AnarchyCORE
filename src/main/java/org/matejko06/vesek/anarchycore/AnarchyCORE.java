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
    AcCommand acorec = new AcCommand(this);
    AcCommand ac = new AcCommand(this);
    EventsClass events = new EventsClass(this);

    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(text);
    }
    private ConfigManager cfgm;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(events, this);
        command_preprocessing = getConfig().getBoolean("command-preprocessing");
        loadConfigManager();
        getServer().getPluginManager().registerEvents(new EventsClass(this), this);
        loadConfig();
        getCommand("tps").setExecutor(tc);
        getCommand("queue").setExecutor(qc);
        getCommand("kill").setExecutor(kc);
        getCommand("info").setExecutor(ic);
        getCommand("help").setExecutor(hc);
        getCommand("acore").setExecutor(acorec);
        getCommand("ac").setExecutor(ac);
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
        saveConfig();
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
                return tab;
            }
        }
        return null;
    }
}