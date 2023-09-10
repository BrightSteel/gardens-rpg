package com.gardensmc.gardensrpg.placeholders;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.database.entry.PlayerEntry;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class RPGExpansion extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "GardensRPG";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Bright_Steel";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (player == null) return null;

        if (params.equalsIgnoreCase("mana")) {
            CompletableFuture<PlayerEntry> playerEntry = GardensRPG.playerEntryCache.get(player.getUniqueId());
            try {
                return String.valueOf(playerEntry.get().getMana());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (params.equalsIgnoreCase("mana_bar")) {
            // TODO return mana bar based on configured image unicodes
        }

        return null;
    }
}
