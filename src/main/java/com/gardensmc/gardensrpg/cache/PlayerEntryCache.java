package com.gardensmc.gardensrpg.cache;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.database.entry.PlayerBladeBringerEntry;
import com.gardensmc.gardensrpg.database.table.Tables;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

public class PlayerEntryCache extends AsyncCache<UUID, List<PlayerBladeBringerEntry>> {

    @Override
    public CompletableFuture<List<PlayerBladeBringerEntry>> createCacheEntry(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> Tables.playerBladeBringerTable.getEntries(uuid.toString()));
    }

    @Nullable
    public PlayerBladeBringerEntry getActiveBladeBringer(UUID uuid) {
        CompletableFuture<List<PlayerBladeBringerEntry>> entries = get(uuid);
        try {
            return entries.get().stream()
                    .filter(PlayerBladeBringerEntry::isActive)
                    .findFirst().orElse(null);
        } catch (Exception e) {
            GardensRPG.plugin.getLogger().log(Level.SEVERE, "Failed to get active blade bringer", e);
            return null;
        }
    }
}
