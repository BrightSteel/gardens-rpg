package com.gardensmc.gardensrpg.cache;

import com.gardensmc.gardensrpg.cooldown.AbilityCooldowns;

import java.util.*;

public class PlayerCooldownsCache extends Cache<UUID, AbilityCooldowns> {

    @Override
    AbilityCooldowns createCacheEntry(UUID key) {
        return new AbilityCooldowns();
    }

    public void tickCache() {
        cache.asMap().values()
                .forEach(AbilityCooldowns::tickCooldowns);
    }
}
