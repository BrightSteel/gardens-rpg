package com.gardensmc.gardensrpg.bladebringers;

import com.gardensmc.gardensrpg.ability.types.Ability;
import com.gardensmc.gardensrpg.attribute.AttributeGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class BladeBringer {
    private AttributeGroup baseAttributes, maxAttributes;
    private Ability passiveAbility, activeAbility;
}
