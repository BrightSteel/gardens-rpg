package com.gardensmc.gardensrpg.ability;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.ability.types.Ability;
import com.gardensmc.gardensrpg.ability.types.Cast;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public abstract class ParticleProjectileAbility extends Ability implements Cast {

    private static final int MAX_LENGTH = 100;

    public abstract void onHitEntity(Player caster, LivingEntity entity);

    public abstract Particle getParticle();

    public Object getParticleOptions() {
        return null;
    }

    public double getSpeed() {
        return 1;
    }

    @Override
    public void cast(Player player) {
        new BukkitRunnable() {
            private final World world = player.getWorld();
            private final Vector direction = player.getLocation().getDirection().normalize();
            private final Location projectileLocation = player.getEyeLocation();
            private int projectileLength = 0;

            @Override
            public void run() {
                List<Entity> nearbyLiving = world.getNearbyEntities(
                        projectileLocation,
                        1, 1, 1,
                        entity -> entity instanceof LivingEntity && !entity.getUniqueId().equals(player.getUniqueId())
                ).stream().toList();

                if (!nearbyLiving.isEmpty()) {
                    this.cancel();
                    onHitEntity(player, (LivingEntity) nearbyLiving.get(0));
                } else if (projectileLength >= MAX_LENGTH) {
                    this.cancel();
                } else if (projectileLocation.getBlock().getType() != Material.AIR) {
                    this.cancel();
                } else {
                    projectileLength++;
                    world.spawnParticle(
                            getParticle(),
                            projectileLocation,
                            5,
                            0.1, 0.1, 0.1,
                            5,
                            getParticleOptions()
                    );
                    projectileLocation.add(direction.clone().multiply(getSpeed()));
                }

            }
        }.runTaskTimer(GardensRPG.plugin, 0, 1);
    }
}
