package com.gardensmc.gardensrpg.cmd;

import com.gardensmc.gardensrpg.GardensRPG;
import org.bukkit.command.CommandExecutor;

import java.util.*;

public class CommandHandler {

    private static final HashMap<String, CommandExecutor> commands = new HashMap<>();

    public static void registerCommands() {
        createCommands();
        for (Map.Entry<String, CommandExecutor> entry : commands.entrySet()) {
            Objects.requireNonNull(GardensRPG.plugin.getCommand(entry.getKey())).setExecutor(entry.getValue());
        }
    }

    private static void createCommands() {
        commands.put("bladebringer", new BladeBringerCmd());
        commands.put("playerprofile", new PlayerProfileCmd());
    }
}
