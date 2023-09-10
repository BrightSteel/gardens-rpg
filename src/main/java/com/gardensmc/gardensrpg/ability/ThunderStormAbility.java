package com.gardensmc.gardensrpg.ability;

import com.gardensmc.gardensrpg.ability.types.Ability;
import com.gardensmc.gardensrpg.ability.types.Cast;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ThunderStormAbility extends Ability implements Cast {

    private static final int MAX_RANGE = 100;

    @Override
    public int getCoolDown() {
        return 10;
    }

    @Override
    public void cast(Player player) {
        Location cursor = player.getTargetBlock(null, MAX_RANGE).getLocation();
        player.getWorld().strikeLightning(cursor);
    }
}
