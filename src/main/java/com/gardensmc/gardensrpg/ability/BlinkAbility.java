package com.gardensmc.gardensrpg.ability;

import com.gardensmc.gardensrpg.ability.types.Ability;
import com.gardensmc.gardensrpg.ability.types.Cast;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BlinkAbility extends Ability implements Cast {

    private static final int BLINK_DISTANCE = 4;

    // todo add particle & sound effect on teleport
    @Override
    public void cast(Player player) {
        Location playerLocation = player.getLocation();
        Location blinkLocation = playerLocation.clone().add(playerLocation.getDirection().multiply(BLINK_DISTANCE));
        player.teleport(blinkLocation);
    }

    @Override
    public int getCoolDown() {
        return 5;
    }
}
