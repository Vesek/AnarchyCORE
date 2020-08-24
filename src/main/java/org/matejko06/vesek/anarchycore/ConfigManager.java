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

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        messagesfile = new File(plugin.getDataFolder(), "messages.yml");

        if (!messagesfile.exists()) {
            try {
                messagesfile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThe &6players.yml&a file has been created!"));
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender()
                        .sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &cCould not create the &6messages.yml&c file!"));
            }
        }

        messagescfg = YamlConfiguration.loadConfiguration(messagesfile);
    }

    public FileConfiguration getMessages() {
        return messagescfg;
    }

    public void saveMessages() {
        try {
            messagescfg.save(messagesfile);
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThe &6messages.yml&a file has been saved!"));

        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCCORE&7: &cCould not save the &6messages.yml&c file!"));
        }
    }

    public void reloadMessages() {
        messagescfg = YamlConfiguration.loadConfiguration(messagesfile);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAnarchyCORE&7: &aThe &6messages.yml&a file has been reloaded!"));

    }
}