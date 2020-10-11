package org.matejko06.vesek.anarchycore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.matejko06.vesek.anarchycore.AnarchyCORE;

public class IgnoreCommand implements CommandExecutor {

    AnarchyCORE ac;

    public IgnoreCommand(AnarchyCORE ac) {
        this.ac = ac;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}
