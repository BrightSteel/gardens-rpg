package com.gardensmc.gardensrpg.util;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.database.entry.PlayerBladeBringerEntry;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.logging.Level;

public class AttributeUtil {

    public static void syncAttributes(Player player) {
        try {
            PlayerBladeBringerEntry playerBladeBringerEntry = GardensRPG.playerEntryCache.getActiveBladeBringer(player.getUniqueId());
            if (playerBladeBringerEntry != null) {
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerBladeBringerEntry.getHp());
                player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(playerBladeBringerEntry.getSpeed());
                player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(playerBladeBringerEntry.getAttack_damage());
            }
        } catch (Exception e) {
            GardensRPG.plugin.getLogger().log(Level.WARNING, "Failed to sync attributes for player " + player.getName(), e);
        }
    }
}
