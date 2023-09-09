package com.gardensmc.gardensrpg.cache;

import com.gardensmc.gardensrpg.database.entry.PlayerEntry;
import com.gardensmc.gardensrpg.database.table.Tables;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PlayerEntryCache extends AsyncCache<UUID, PlayerEntry> {

    @Override
    public CompletableFuture<PlayerEntry> createCacheEntry(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> Tables.playerTable.getPlayerEntry(uuid.toString()));
    }
}
