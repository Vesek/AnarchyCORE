package org.matejko06.vesek.anarchycore;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
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

    @Getter private ConfigManager cfgm;

    @Override
    public void onEnable() {
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a is turning on..."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading all &6configs&a..."));
        getConfig().options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(events, this);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded all &6configs&a."));
        command_preprocessing = getConfig().getBoolean("command-preprocessing");
        loadConfigs();
        getCommand("tps").setExecutor(tc);
        getCommand("priority").setExecutor(qc);
        getCommand("kill").setExecutor(kc);
        getCommand("info").setExecutor(ic);
        getCommand("help").setExecutor(hc);
        getCommand("ac").setExecutor(ac);
        Bukkit.getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(this, this);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a turned on!"));
        log(ChatColor.translateAlternateColorCodes('&', " "));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aChecking for update..."));

        new UpdateChecker(this, 82999).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &cThere is no new update available."));
            } else {
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThere is a new update available!"));
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &cCurrent version: &6" + this.getDescription().getVersion() + " &7&l| &aNew version: &6" + version));
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aDownload it at: &3https://www.spigotmc.org/resources/anarchycore.82999"));
            }
        });
    }

    @Override
    public void onDisable() {
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&a is turning off..."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Saving all &6configs&a..."));
        saveConfig();
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully saved all &6configs&a."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&c turned off!"));
    }

    public void loadConfigs() {
        cfgm = new ConfigManager();
        cfgm.setup();
        getConfig().options().copyDefaults(true);
        saveConfig();
        if(cfgm.deathmessagescfg == null){
            log(ChatColor.translateAlternateColorCodes('&', "&6&lGEIIIIIIIIII"));
        }
        cfgm.deathmessagescfg.options().copyDefaults(true);
        cfgm.saveConfigs();
        cfgm.messagescfg.options().copyDefaults(true);
        cfgm.saveConfigs();
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