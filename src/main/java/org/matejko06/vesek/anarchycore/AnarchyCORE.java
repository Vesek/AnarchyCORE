package org.matejko06.vesek.anarchycore;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;
import org.bukkit.configuration.file.YamlConstructor;
import org.bukkit.configuration.file.YamlRepresenter;
import org.matejko06.vesek.anarchycore.commands.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class AnarchyCORE extends JavaPlugin implements Listener {

    public static boolean command_preprocessing = false;

    TPSCommand tc = new TPSCommand(this);
    QueueCommand qc = new QueueCommand(this);
    KillCommand kc = new KillCommand(this);
    InfoCommand ic = new InfoCommand(this);
    HelpCommand hc = new HelpCommand(this);
    AcoreCommand acorec = new AcoreCommand(this);
    Events events = new Events(this);

    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(text);
    }

    private File messageFile;
    private static FileConfiguration messageConfig;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(events, this);
        command_preprocessing = getConfig().getBoolean("command-preprocessing");
        getCommand("tps").setExecutor(tc);
        getCommand("queue").setExecutor(qc);
        getCommand("kill").setExecutor(kc);
        getCommand("info").setExecutor(ic);
        getCommand("help").setExecutor(hc);
        getCommand("acore").setExecutor(acorec);
        messageFile = new File(getDataFolder(),"messages.yml");
        if(!messageFile.exists()){
            try {
                messageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        messageConfig = YamlConfiguration.loadConfiguration(messageFile);
        Bukkit.getPluginManager().registerEvents(this, this);
        log(ChatColor.translateAlternateColorCodes('&',"&6&lAnarchyCORE&a turned on!"));
    }

    @Override
    public void onDisable() {
        saveConfig();
        log(ChatColor.translateAlternateColorCodes('&',"&6&lAnarchyCORE&c turned off!"));

    }

    public static FileConfiguration getMessageConfig(){
        return messageConfig;
    }

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