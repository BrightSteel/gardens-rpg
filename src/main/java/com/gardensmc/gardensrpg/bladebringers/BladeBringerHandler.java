package com.gardensmc.gardensrpg.bladebringers;

import com.gardensmc.gardensrpg.ability.Abilities;
import com.gardensmc.gardensrpg.attribute.AttributeGroup;

import java.util.HashMap;
import java.util.Map;

public class BladeBringerHandler {
    private final Map<String, BladeBringer> bladeBringersMap;

    public BladeBringerHandler() {
        this.bladeBringersMap = new HashMap<>();
        createBladeBringers();
    }

    public BladeBringer getBladeBringer(String name) {
        return bladeBringersMap.get(name);
    }

    private void createBladeBringers() {
        bladeBringersMap.put(
                "SwiftBlade",
                new BladeBringer(
                    new AttributeGroup(16, 20, 20, 1.6, 2.6, 0.05),
                    new AttributeGroup(23, 25, 25, 2.4, 3.4, 0.20),
                    Abilities.evasiveManeuversAbility,
                    Abilities.blinkStrikeAbility
        ));
    }
}
