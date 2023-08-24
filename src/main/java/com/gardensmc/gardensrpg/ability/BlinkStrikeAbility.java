package com.gardensmc.gardensrpg.ability;

import com.gardensmc.gardensrpg.ability.types.Ability;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BlinkStrikeAbility extends Ability {
    @Override
    public void cast(Player player) {
        Location playerLocation = player.getLocation();
        Location blinkLocation = playerLocation.clone().add(playerLocation.getDirection().multiply(4));
        player.teleport(blinkLocation);
    }

    @Override
    public int getCoolDown() {
        return 20;
    }
}
