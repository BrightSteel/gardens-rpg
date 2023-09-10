package com.gardensmc.gardensrpg.bladebringers;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.ability.Abilities;
import com.gardensmc.gardensrpg.attribute.AttributeGroup;
import com.gardensmc.gardensrpg.database.entry.PlayerEntry;
import com.gardensmc.gardensrpg.database.table.Tables;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BladeBringerHandler {
    private final Map<String, BladeBringer> bladeBringersMap;

    public BladeBringerHandler() {
        this.bladeBringersMap = new HashMap<>();
        createBladeBringers();
    }

    public BladeBringer getBladeBringer(String name) {
        return bladeBringersMap.get(name);
    }

    public Set<Map.Entry<String, BladeBringer>> getBladeBringers() {
        return bladeBringersMap.entrySet();
    }

    public boolean setBladeBringer(Player player, String bladeBringerName) throws ExecutionException, InterruptedException {
        BladeBringer bladeBringer = bladeBringersMap.get(bladeBringerName);
        if (bladeBringer == null) {
            GardensRPG.plugin.getLogger().info("Failed to find blade bringer: " + bladeBringerName);
            return false;
        }
        boolean success = CompletableFuture.supplyAsync(
                () -> Tables.playerTable.setOrCreatePlayerEntry(
                        player.getUniqueId().toString(),
                        new PlayerEntry(bladeBringerName, bladeBringer.getBaseAttributes())
                )).get();
        if (success) {
            GardensRPG.playerEntryCache.remove(player.getUniqueId());
        }
        return success;
    }

    private void createBladeBringers() {
        bladeBringersMap.put(
                "SwiftBlade",
                new BladeBringer(
                        new AttributeGroup(16, 20, 20, 1.6, 2.6, 0.05),
                        new AttributeGroup(23, 25, 25, 2.4, 3.4, 0.20),
                        Abilities.evasiveManeuversAbility,
                        Abilities.blinkStrikeAbility
                ));
        bladeBringersMap.put(
                "StormCaller",
                new BladeBringer(
                        new AttributeGroup(18, 20, 20, 1.2, 1.8, 0.10),
                        new AttributeGroup(25, 25, 25, 2.0, 2.6, 0.25),
                        Abilities.shockAbility,
                        Abilities.thunderStormAbility
                ));
    }
}
