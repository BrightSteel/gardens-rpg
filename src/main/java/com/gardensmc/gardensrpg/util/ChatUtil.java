package com.gardensmc.gardensrpg.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtil {

    private static final String CHAT_PREFIX = ChatColor.LIGHT_PURPLE + "";

    public static void sendMessage(Player player, String message) {
        player.sendMessage(CHAT_PREFIX + message);
    }
}
