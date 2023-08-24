package com.gardensmc.gardensrpg.attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class AttributeGroup {
    private int health, mana, power;
    private double speed, damage, defense;
}
