package com.gardensmc.gardensrpg.ability;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.ability.types.Ability;
import com.gardensmc.gardensrpg.ability.types.AbilityType;
import com.gardensmc.gardensrpg.ability.types.Cast;
import com.gardensmc.gardensrpg.bladebringers.BladeBringer;
import com.gardensmc.gardensrpg.cooldown.AbilityCooldowns;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CastActiveAbilityHandler {

    public void castActive(Player player) {
        GardensRPG.playerEntryCache.get(player.getUniqueId()).thenAccept(playerEntry -> {
            BladeBringer bladeBringer = GardensRPG.bladeBringerHandler.getBladeBringer(playerEntry.getBladeBringer());
            Ability activeAbility = bladeBringer.getActiveAbility();
            if (activeAbility instanceof Cast castAbility) {
                AbilityCooldowns abilityCooldowns = GardensRPG.playerCooldownsCache.get(player.getUniqueId());
                if (abilityCooldowns.getCooldown(AbilityType.ACTIVE) == 0) {
                    Bukkit.getScheduler().runTask(GardensRPG.plugin, () -> castAbility.cast(player));
                    abilityCooldowns.restartCooldown(AbilityType.ACTIVE, activeAbility.getCoolDown());
                }
            }
        });
    }
}
