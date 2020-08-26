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
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aPlugin is turning on..."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aLoading all &6configs&a..."));
        getConfig().options().copyDefaults(true);
        getCfgm().messagescfg.options().copyDefaults(true);
        getCfgm().deathmessagescfg.options().copyDefaults(true);
        saveConfig();
        getCfgm().saveConfigs();
        getServer().getPluginManager().registerEvents(events, this);
        log(ChatColor.translateAlternateColorCodes('&', " "));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aSuccessfully loaded all &6configs&a."));
        command_preprocessing = getConfig().getBoolean("command-preprocessing");
        loadConfigManager();
        loadConfig();
        getCommand("tps").setExecutor(tc);
        getCommand("priority").setExecutor(qc);
        getCommand("kill").setExecutor(kc);
        getCommand("info").setExecutor(ic);
        getCommand("help").setExecutor(hc);
        getCommand("ac").setExecutor(ac);
        Bukkit.getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(this, this);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aPlugin has turned on!"));
        log(ChatColor.translateAlternateColorCodes('&', " "));
        new UpdateChecker(this, 82999).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aChecking for updates..."));
                log(ChatColor.translateAlternateColorCodes('&', " "));
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &cThere is no new update available."));
            } else {
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aChecking for updates..."));
                log(ChatColor.translateAlternateColorCodes('&', " "));
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThere is a new update available!"));
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &cCurrent version: &6" + this.getDescription().getVersion() + " &7&l| &aNew version: &6" + version));
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aDownload it at: &3https://www.spigotmc.org/resources/anarchycore.82999"));
            }
        });
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        //e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&3" + p.getDisplayName() + "&7 " + getCfgm().messagescfg.getString("join-message")));
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&3" + p.getDisplayName() + "&7 " + getConfig().getString("join-message")));
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
        Player p = e.getPlayer();
        //e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&3" + p.getDisplayName() + "&7 " + getCfgm().messagescfg.getString("leave-message")));
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&3" + p.getDisplayName() + "&7 " + getConfig().getString("leave-message")));
    }

    @Override
    public void onDisable() {
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a is turning off..."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Saving all &6configs&a..."));
        saveConfig();
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully saved all &6configs&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&c turned off!"));
    }

    public void loadConfigManager() {
        cfgm = new ConfigManager();
        cfgm.setup();

    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public ConfigManager getCfgm(){
        return cfgm;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("kill") || command.getName().equalsIgnoreCase("suicide"))
                if (sender.hasPermission("AnarchyCORE.killSomeone") || sender.isOp()) {
                    List<String> tab = new ArrayList<>();
                    for (Player p : getServer().getOnlinePlayers()) {
                        tab.add(p.getDisplayName());
                    }
                    return tab;
                }
            if (command.getName().equalsIgnoreCase("info")) {
                List<String> tab = new ArrayList<>();
                for (Player p : getServer().getOnlinePlayers()) {
                    tab.add(p.getDisplayName());
                }
                return tab;
            }
        } else if (command.getName().equalsIgnoreCase("acore")) {
                    List<String> tab = new ArrayList<>();
                    tab.add("reload");
                    tab.add("help");
                    tab.add("version");
                    tab.add("discord");
                    tab.add("adminhelp");
                    tab.add("support");
                    return tab;
                } else if (command.getName().equalsIgnoreCase("ac")) {
                    List<String> tab = new ArrayList<>();
                    tab.add("reload");
                    tab.add("help");
                    tab.add("version");
                    tab.add("discord");
                    tab.add("adminhelp");
                    tab.add("support");
                    return tab;
                }
            return null;
        }
    }