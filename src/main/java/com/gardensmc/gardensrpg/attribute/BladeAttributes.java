package com.gardensmc.gardensrpg.attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class BladeAttributes {
    private int hp, essence, blade;
    private double speed, attack_damage, defense;
    private boolean active;
    private int playtime;

    public BladeAttributes(int hp, int essence, int blade, double speed, double attack_damage, double defense) {
        this.hp = hp;
        this.essence = essence;
        this.blade = blade;
        this.speed = speed;
        this.attack_damage = attack_damage;
        this.defense = defense;
    }
}
