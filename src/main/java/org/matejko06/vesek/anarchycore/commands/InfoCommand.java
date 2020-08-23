package org.matejko06.vesek.anarchycore.commands;

import org.apache.commons.io.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class InfoCommand implements CommandExecutor {
    Plugin plugin;

    public InfoCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("info")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                String s = plugin.getConfig().getString("info-message");
                try {
                    File world = p.getWorld().getWorldFolder();
                    File playerData = new File(world.getAbsolutePath() + "/playerdata");
                    File data = new File(plugin.getServer().getWorldContainer().getAbsolutePath() + "/eula.txt");
                    BasicFileAttributes attr = Files.readAttributes(data.toPath(), BasicFileAttributes.class);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date firstDate = sdf.parse(attr.creationTime().toString());
                    Date secondDate = sdf.parse(LocalDate.now().toString());
                    int diffInDays = (int) ((secondDate.getTime() - firstDate.getTime()) / 86400000L);
                    StringBuilder time = new StringBuilder();
                    String[] sa = playerData.list();
                    int people = 0;
                    if (sa != null) {
                        for (String sas : sa) {
                            if (sas.split("\\.")[1].equals("dat")) {
                                people++;
                            }
                        }
                    }
                    if (diffInDays / 365 < 1) {
                        if (diffInDays / 30 >= 1) {
                            time.append(diffInDays / 30).append(" months and ");
                            diffInDays %= 30;
                        }
                        time.append(diffInDays).append(" days");
                    } else if (diffInDays / 365 == 1) {
                        diffInDays -= 365;
                        time.append("1 year and ");
                        time.append(diffInDays / 30).append(" months");
                    } else if (diffInDays / 365 > 1) {
                        time.append(diffInDays / 365).append(" years and ");
                        diffInDays %= 365;
                        time.append(diffInDays / 30).append(" months");
                    }
                    s = s.replace("<players>", String.valueOf(people));
                    s = s.replace("<time>", time.toString());
                    s = s.replace("<size>", FileUtils.byteCountToDisplaySize(world.length()));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}