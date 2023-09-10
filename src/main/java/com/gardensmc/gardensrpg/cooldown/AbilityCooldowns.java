package com.gardensmc.gardensrpg.cooldown;

import com.gardensmc.gardensrpg.ability.types.AbilityType;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class AbilityCooldowns {

    private final HashMap<AbilityType, Integer> cooldowns = new HashMap<>(Map.of(
            AbilityType.ACTIVE, 0,
            AbilityType.PASSIVE, 0
    ));

    public int getCooldown(AbilityType abilityType) {
        return cooldowns.get(abilityType);
    }

    public void restartCooldown(AbilityType abilityType, int cooldown) {
        cooldowns.put(abilityType, cooldown);
    }

    public void tickCooldowns() {
        for (Map.Entry<AbilityType, Integer> entry : cooldowns.entrySet()) {
            if (entry.getValue() > 0) {
                entry.setValue(entry.getValue() - 1);
            }
        }
    }
}
