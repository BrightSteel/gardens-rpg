package com.gardensmc.gardensrpg.cmd;

import com.gardensmc.gardensrpg.gui.BladeBringerMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BladeBringerCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("Only players can run this command!");
            return true;
        }

        new BladeBringerMenu(player).open();
        return true;
    }
}
