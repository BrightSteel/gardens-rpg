package com.gardensmc.gardensrpg.bladebringers;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.ability.Abilities;
import com.gardensmc.gardensrpg.attribute.BladeAttributes;
import com.gardensmc.gardensrpg.database.entry.PlayerBladeBringerEntry;
import com.gardensmc.gardensrpg.database.table.Tables;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BladeBringerHandler {

    public static final String SWIFT_BLADE = "SwiftBlade";
    public static final String STORM_CALLER = "StormCaller";
    public static final String NECROMANCER = "Necromancer";
    public static final String MAGE = "Mage";

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

    // TODO if this is their first blade bringer, automatically set active to true in base attributes
    public boolean createBladeBringer(Player player, String bladeBringerName) throws ExecutionException, InterruptedException {
        BladeBringer bladeBringer = bladeBringersMap.get(bladeBringerName);
        if (bladeBringer == null) {
            GardensRPG.plugin.getLogger().info("Failed to find blade bringer: " + bladeBringerName);
            return false;
        }
        boolean success = CompletableFuture.supplyAsync(
                () -> Tables.playerBladeBringerTable.createEntry(
                        player.getUniqueId().toString(),
                        new PlayerBladeBringerEntry(bladeBringerName, bladeBringer.getBaseAttributes())
                )).get();
        if (success) {
            GardensRPG.playerEntryCache.remove(player.getUniqueId());
        }
        return success;
    }

    private void createBladeBringers() {
        bladeBringersMap.put(
                SWIFT_BLADE,
                new BladeBringer(
                        new BladeAttributes(16, 20, 20, 1.6, 2.6, 0.05),
                        new BladeAttributes(23, 25, 25, 2.4, 3.4, 0.20),
                        Abilities.evasiveManeuversAbility,
                        Abilities.blinkStrikeAbility
                ));
        bladeBringersMap.put(
                STORM_CALLER,
                new BladeBringer(
                        new BladeAttributes(18, 20, 20, 1.2, 1.8, 0.10),
                        new BladeAttributes(25, 25, 25, 2.0, 2.6, 0.25),
                        Abilities.shockAbility,
                        Abilities.thunderStormAbility
                ));
        bladeBringersMap.put(
                NECROMANCER,
                new BladeBringer(
                        new BladeAttributes(18, 20, 20, 1.2, 1.8, 0.10),
                        new BladeAttributes(25, 25, 25, 2.0, 2.6, 0.25),
                        Abilities.soulShroudAbility,
                        Abilities.necroCallAbility
                )
        );
        bladeBringersMap.put(
                MAGE,
                new BladeBringer(
                        new BladeAttributes(18, 20, 20, 1.2, 1.8, 0.10),
                        new BladeAttributes(25, 25, 25, 2.0, 2.6, 0.25),
                        Abilities.soulShroudAbility,
                        Abilities.mageProjectileAbility
                )
        );
    }
}
