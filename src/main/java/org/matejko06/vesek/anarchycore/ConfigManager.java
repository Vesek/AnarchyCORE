package org.matejko06.vesek.anarchycore;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {

    private AnarchyCORE plugin = AnarchyCORE.getPlugin(AnarchyCORE.class);

    public FileConfiguration messagescfg;
    public File messagesfile;
    public FileConfiguration deathmessagescfg;
    public File deathmessagesfile;
    public FileConfiguration tabconfigcfg;
    public File tabconfigfile;

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        messagesfile = new File(plugin.getDataFolder(), "messages.yml");
        deathmessagesfile = new File(plugin.getDataFolder(), "deathmessages.yml");
        tabconfigfile = new File(plugin.getDataFolder(), "tabconfig.yml");

        if (!messagesfile.exists()) {
            try {
                messagesfile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThe &6messagess.yml&a file has been created!"));
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender()
                        .sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &cCould not create the &6messages.yml&c file!"));
            }
        }
        messagescfg = YamlConfiguration.loadConfiguration(messagesfile);

        if (!deathmessagesfile.exists()) {
            try {
                deathmessagesfile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThe &6deathmessages.yml&a file has been created!"));
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender()
                        .sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &cCould not create the &6deathmessages.yml&c file!"));
            }
        }
            deathmessagescfg = YamlConfiguration.loadConfiguration(deathmessagesfile);

        if (!tabconfigfile.exists()) {
            try {
                tabconfigfile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThe &6tabconfig.yml&a file has been created!"));
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender()
                        .sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &cCould not create the &6tabconfig.yml&c file!"));
            }
            tabconfigcfg = YamlConfiguration.loadConfiguration(tabconfigfile);
        }
    }

    public void saveConfigs() {
        try {
            messagescfg.save(messagesfile);
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThe &6messages.yml&a file has been saved!"));
            deathmessagescfg.save(deathmessagesfile);
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThe &6deathmessages.yml&a file has been saved!"));
            tabconfigcfg.save(tabconfigfile);
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThe &6tabconfig.yml&a file has been saved!"));

        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCCORE&7: &cCould not save config files!"));
        }
    }
    public void reloadConfigs() {
        messagescfg = YamlConfiguration.loadConfiguration(messagesfile);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThe &6messages.yml&a file has been reloaded!"));
        deathmessagescfg = YamlConfiguration.loadConfiguration(deathmessagesfile);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThe &6deathmessages.yml&a file has been reloaded!"));
        tabconfigcfg = YamlConfiguration.loadConfiguration(tabconfigfile);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThe &6tabconfig.yml&a file has been reloaded!"));
    }
}