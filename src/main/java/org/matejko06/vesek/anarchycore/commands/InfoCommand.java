package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class InfoCommand implements CommandExecutor {
    AnarchyCORE ac;

    public InfoCommand(AnarchyCORE ac) {
        this.ac = ac;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("info")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                String s = ac.getConfig().getString("info-message");
                //String s = ac.getCfgm().messagescfg.getString("info-message");
                try {
                    File world = p.getWorld().getWorldFolder();
                    File playerData = new File(world.getAbsolutePath() + "/playerdata");
                    File data = new File(ac.getServer().getWorldContainer().getAbsolutePath() + "/eula.txt");
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
                    Long length = folderSize(world);
                    StringBuilder sb = new StringBuilder();
                    if((length >= 1073741824)){
                        sb.append(length/1073741824).append(" GB and ");
                        length %= 1073741824;
                    }
                    sb.append(length/1048576).append(" MB");
                    s = s.replace("<players>", String.valueOf(people));
                    s = s.replace("<time>", time.toString());
                    s = s.replace("<size>", sb.toString());
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static long folderSize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                length += file.length();
            else
                length += folderSize(file);
        }
        return length;
    }
}