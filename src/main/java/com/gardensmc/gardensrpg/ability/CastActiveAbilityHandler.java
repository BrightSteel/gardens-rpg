package com.gardensmc.gardensrpg.ability;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.ability.types.Cast;
import com.gardensmc.gardensrpg.bladebringers.BladeBringer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CastActiveAbilityHandler {

    private final HashSet<UUID> primedPlayers = new HashSet<>();

    public void toggleAbility(Player player) {
        if (primedPlayers.contains(player.getUniqueId())) {
            primedPlayers.remove(player.getUniqueId());
            player.sendMessage("Deprimed active ability");
        } else {
            primedPlayers.add(player.getUniqueId());
            player.sendMessage("Primed active ability");
        }
    }

    public void castIfPrimed(Player player) {
        if (primedPlayers.contains(player.getUniqueId())) {
            GardensRPG.playerEntryCache.get(player.getUniqueId()).thenAccept(playerEntry -> {
                BladeBringer bladeBringer = GardensRPG.bladeBringerHandler.getBladeBringer(playerEntry.getBladeBringer());
                if (bladeBringer.getActiveAbility() instanceof Cast castAbility) {
                    Bukkit.getScheduler().runTask(GardensRPG.plugin, () -> castAbility.cast(player));
                }
            });
            primedPlayers.remove(player.getUniqueId());
        }
    }
}
