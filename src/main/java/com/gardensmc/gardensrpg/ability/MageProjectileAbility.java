package com.gardensmc.gardensrpg.ability;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class MageProjectileAbility extends ParticleProjectileAbility {

    @Override
    public void onHitEntity(Player caster, LivingEntity entity) {
        entity.damage(1, caster);
    }

    @Override
    public Particle getParticle() {
        return Particle.REDSTONE;
    }

    @Override
    public Object getParticleOptions() {
        return new Particle.DustOptions(Color.RED, 1);
    }

    @Override
    public int getCoolDown() {
        return 0;
    }

    @Override
    public double getSpeed() {
        return 0.5;
    }
}
