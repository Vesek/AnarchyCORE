package org.matejko06.vesek.anarchycore;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.matejko06.vesek.anarchycore.commands.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class AnarchyCORE extends JavaPlugin implements Listener {

    public FileConfiguration messagescfg;
    public File messagesfile;
    public FileConfiguration deathmessagescfg;
    public File deathmessagesfile;

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

    @SneakyThrows
    @Override
    public void onEnable() {
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Plugin is turning on..."));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Loading all &6configs&a..."));

        messagesfile = new File(getDataFolder(), "messages.yml");
        deathmessagesfile = new File(getDataFolder(), "DeathMessages.yml");
        messagescfg = new YamlConfiguration();
        deathmessagescfg = new YamlConfiguration();

        if (!messagesfile.exists()) {
            saveResource("messages.yml",false);
        }
        messagescfg.load(messagesfile);

        if (!deathmessagesfile.exists()) {
            saveResource("DeathMessages.yml",false);
        }
        deathmessagescfg.load(deathmessagesfile);

        getConfig().options().copyDefaults(true);
        saveConfig();
        deathmessagescfg.options().copyDefaults(true);
        deathmessagescfg.save(deathmessagesfile);
        messagescfg.options().copyDefaults(true);
        messagescfg.save(messagesfile);

        getServer().getPluginManager().registerEvents(events, this);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Successfully loaded all &6configs&a."));
        command_preprocessing = getConfig().getBoolean("Command-Preprocessing");
        getCommand("tps").setExecutor(tc);
        getCommand("priority").setExecutor(qc);
        getCommand("kill").setExecutor(kc);
        getCommand("info").setExecutor(ic);
        getCommand("help").setExecutor(hc);
        getCommand("ac").setExecutor(ac);
        Bukkit.getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(this, this);
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7:&a Plugin turned on!"));
        log(ChatColor.translateAlternateColorCodes('&', " "));
        log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aChecking for update..."));

        new UpdateChecker(this, 82999).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                log(ChatColor.translateAlternateColorCodes('&', " "));
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &cThere is no new update available."));
            } else {
                log(ChatColor.translateAlternateColorCodes('&', " "));
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThere is a new update available!"));
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &cCurrent version: &6" + this.getDescription().getVersion() + " &7&l| &aNew version: &6" + version));
                log(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aDownload it at: &3https://www.spigotmc.org/resources/anarchycore.82999"));
                log(ChatColor.translateAlternateColorCodes('&', " "));
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