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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class AnarchyCORE extends JavaPlugin implements Listener {

    boolean command_preprocessing = false;

    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(text);
    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(this, this);
        command_preprocessing = getConfig().getBoolean("command-preprocessing");
        log("AnarchyCORE turned on!");
    }

    @Override
    public void onDisable() {
        log("AnarchyCORE turned off!");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kill")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 0) {
                    if (p.hasPermission("AnarchyCORE.kill") || p.isOp()) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("kill-message")));
                        p.setHealth(0.00);
                    }
                }
                if (args.length >= 1) {
                    if (p.hasPermission("AnarchyCORE.killSomeone") || p.isOp()) {
                        for (String s : args) {
                            Player victim = getServer().getPlayer(s);
                            if (victim != null) {
                                victim.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("killsomeone-message")));
                                victim.setHealth(0.00);
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage("Only players can use this command!");
            }
        } else if (command.getName().equalsIgnoreCase("tps")) {
            if (sender.hasPermission("AnarchyCORE.tps") || sender.isOp()) {
                StringBuilder sb = new StringBuilder();
                double[] TPS = getServer().getTPS();
                if (TPS[0] >= getConfig().getDouble("tps-green")) {
                    sb.append(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tps-green-color"))).append(String.format("%.2f", TPS[0]));
                } else if (TPS[0] >= getConfig().getDouble("tps-yellow")) {
                    sb.append(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tps-yellow-color"))).append(String.format("%.2f", TPS[0]));
                } else if (TPS[0] >= 0.00) {
                    sb.append(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tps-message"))).append(ChatColor.translateAlternateColorCodes('&', getConfig().getString("tps-red-color"))).append(String.format("%.2f", TPS[0]));
                }
                sender.sendMessage(sb.toString());
            }
        } else if (command.getName().equalsIgnoreCase("queue")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("AnarchyCOREQueue..admin")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', getConfig().getString("admin-message")));
                } else if (sender.hasPermission("AnarchyCOREQueue..priority")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', getConfig().getString("priority-message")));
                } else if (sender.hasPermission("AnarchyCOREQueue..regular")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("queue-message")) + ChatColor.translateAlternateColorCodes('&', getConfig().getString("regular-message")));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("invalid-command")));
                }
            }
        }  else if (command.getName().equalsIgnoreCase("info") && sender instanceof Player) {
            if(sender instanceof Player){
                Player p = (Player) sender;
                String s = this.getConfig().getString("info-message");
                try {
                    File world = p.getWorld().getWorldFolder();
                    File playerData = new File(world.getAbsolutePath() + "\\playerdata");
                    File data = new File(this.getServer().getWorldContainer().getAbsolutePath() + "/eula.txt");
                    BasicFileAttributes attr = Files.readAttributes(data.toPath(), BasicFileAttributes.class);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date firstDate = sdf.parse(attr.creationTime().toString());
                    Date secondDate = sdf.parse(LocalDate.now().toString());
                    int diffInDays = (int)((secondDate.getTime() - firstDate.getTime()) / 86400000L);
                    StringBuilder time = new StringBuilder();
                    String[] sa = playerData.list();
                    int people = 0;
                    for(String sas : sa){
                        if(sas.split("\\.")[1].equals("dat")){
                            people++;
                        }
                    }
                    if (diffInDays / 365 < 1) {
                        if (diffInDays / 30 < 1) {
                            time.append(diffInDays + " days");
                        }
                        else{
                            time.append(diffInDays / 30 + " months and ");
                            diffInDays %= 30;
                            time.append(diffInDays + " days");
                        }
                    }
                    else if (diffInDays / 365 == 1) {
                        diffInDays -= 365;
                        time.append("1 year and ");
                        time.append(diffInDays / 30 + " months");
                    }
                    else if (diffInDays / 365 > 1) {
                        time.append(diffInDays / 365 + " years and ");
                        diffInDays %= 365;
                        time.append(diffInDays / 30 + " months");
                    }
                    s = s.replace("<players>", String.valueOf(people));
                    s = s.replace("<time>", time.toString());
                    s = s.replace("<size>", FileUtils.byteCountToDisplaySize(world.length()));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
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
        }
        return null;
    }



    @EventHandler
    public void onCommandPreProcess(PlayerCommandPreprocessEvent event){
        if(event.getMessage().toLowerCase().startsWith("/tps") && command_preprocessing){
            event.setCancelled(true);
            event.getPlayer().chat("/anarchycore:tps");
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