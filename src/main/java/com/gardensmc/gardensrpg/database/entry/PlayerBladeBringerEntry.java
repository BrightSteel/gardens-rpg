package com.gardensmc.gardensrpg.database.entry;

import com.gardensmc.gardensrpg.attribute.BladeAttributes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter @Setter
@NoArgsConstructor
public class PlayerBladeBringerEntry extends MySQLEntry {
    private String blade_bringer;
    private int hp, essence, blade;
    private double speed, attack_damage, defense;
    private int playtime;
    private boolean active; // enforce only one can be active at a time

    public PlayerBladeBringerEntry(String bladeBringerName, BladeAttributes bladeAttributes) {
        this.blade_bringer = bladeBringerName;
        this.hp = bladeAttributes.getHp();
        this.essence = bladeAttributes.getEssence();
        this.blade = bladeAttributes.getBlade();
        this.speed = bladeAttributes.getSpeed();
        this.attack_damage = bladeAttributes.getAttack_damage();
        this.defense = bladeAttributes.getDefense();
        this.playtime = bladeAttributes.getPlaytime();
        this.active = bladeAttributes.isActive();
    }

    @Override
    public void populate(ResultSet resultSet) throws SQLException {
        setBlade_bringer(resultSet.getString("blade_bringer"));
        setHp(resultSet.getInt("hp"));
        setEssence(resultSet.getInt("essence"));
        setBlade(resultSet.getInt("blade"));
        setSpeed(resultSet.getDouble("speed"));
        setAttack_damage(resultSet.getDouble("attack_damage"));
        setDefense(resultSet.getDouble("defense"));
        setPlaytime(resultSet.getInt("playtime"));
        setActive(resultSet.getBoolean("active"));
    }
}
