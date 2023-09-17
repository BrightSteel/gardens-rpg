package com.gardensmc.gardensrpg.cmd;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.gui.BladeBringerMenu;
import com.gardensmc.gardensrpg.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BladeBringerCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("Only players can run this command!");
            return true;
        }

        if (args.length > 0 && args[0].equals("reload") && player.hasPermission("gardens.rpg.reload")) {
            GardensRPG.loadConfig();
            ChatUtil.sendMessage(player, "Reloaded config!");
        } else {
            new BladeBringerMenu(player).open();
        }
        return true;
    }
}
