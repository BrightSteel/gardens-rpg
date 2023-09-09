package com.gardensmc.gardensrpg.database.entry;

import com.gardensmc.gardensrpg.attribute.AttributeGroup;
import com.gardensmc.gardensrpg.bladebringers.BladeBringer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter @Setter
@NoArgsConstructor
public class PlayerEntry extends MySQLEntry {
    private String bladeBringer;
    private int health, mana, power;
    private double speed, damage, defense;

    public PlayerEntry(String bladeBringerName, AttributeGroup baseAttributes) {
        this.bladeBringer = bladeBringerName;
        this.health = baseAttributes.getHealth();
        this.mana = baseAttributes.getMana();
        this.power = baseAttributes.getPower();
        this.speed = baseAttributes.getSpeed();
        this.damage = baseAttributes.getDamage();
        this.defense = baseAttributes.getDefense();
    }

    @Override
    public void populate(ResultSet resultSet) throws SQLException {
        setBladeBringer(resultSet.getString("bladeBringer"));
        setHealth(resultSet.getInt("health"));
        setMana(resultSet.getInt("mana"));
        setPower(resultSet.getInt("power"));
        setSpeed(resultSet.getDouble("speed"));
        setDamage(resultSet.getDouble("damage"));
        setDefense(resultSet.getDouble("defense"));
    }
}
