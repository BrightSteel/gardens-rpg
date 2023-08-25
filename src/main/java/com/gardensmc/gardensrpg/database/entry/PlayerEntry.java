package com.gardensmc.gardensrpg.database.entry;

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
