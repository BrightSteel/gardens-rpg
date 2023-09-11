package com.gardensmc.gardensrpg.util;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.attribute.AttributeGroup;
import com.gardensmc.gardensrpg.database.entry.PlayerEntry;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class AttributeUtil {

    public static void syncAttributes(Player player) {
        try {
            PlayerEntry playerEntry = GardensRPG.playerEntryCache.get(player.getUniqueId()).get();
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerEntry.getHealth());
            player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(playerEntry.getSpeed());
            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(playerEntry.getDamage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
