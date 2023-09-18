package com.gardensmc.gardensrpg.placeholders;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.database.entry.PlayerBladeBringerEntry;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

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

        if (params.equalsIgnoreCase("essence")) {
            PlayerBladeBringerEntry entry = GardensRPG.playerEntryCache.getActiveBladeBringer(player.getUniqueId());
            if (entry != null) {
                return String.valueOf(entry.getEssence());
            } else {
                return "0";
            }
        } else if (params.equalsIgnoreCase("essence_bar")) {
            // TODO return mana bar based on configured image unicodes
        }

        return null;
    }
}
