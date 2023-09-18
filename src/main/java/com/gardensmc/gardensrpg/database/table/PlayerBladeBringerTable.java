package com.gardensmc.gardensrpg.database.table;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.database.entry.PlayerBladeBringerEntry;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class PlayerBladeBringerTable extends MySQLTable {

    private static final String prepareStatement = """
            CREATE TABLE IF NOT EXISTS player_blade_bringer(
                player_uuid varchar(36) NOT NULL,
                blade_bringer varchar(50) NOT NULL,
                hp integer NOT NULL,
                attack_damage double NOT NULL,
                defense double NOT NULL,
                blade integer NOT NULL,
                essence integer NOT NULL,
                speed double NOT NULL,
                active boolean NOT NULL,
                playtime integer NOT NULL,
                PRIMARY KEY (player_uuid, blade_bringer)
            )
            """;

    @Override
    public String getPrepareStatement() {
        return prepareStatement;
    }

    public List<PlayerBladeBringerEntry> getEntries(String uuid) {
        try {
            String query = "SELECT * FROM player_blade_bringer WHERE player_uuid = ?";
            PlayerBladeBringerEntry playerBladeBringerEntry = new PlayerBladeBringerEntry();
            return GardensRPG.dbConnector.queryCollection(query, playerBladeBringerEntry, uuid).stream().map(entry -> (PlayerBladeBringerEntry) entry).toList();
        } catch (SQLException e) {
            GardensRPG.plugin.getLogger().log(Level.WARNING, "Failed to query Player BladeBringer Entries", e);
            return null;
        }
    }

    public boolean removeEntry(String uuid, String bladeBringer) {
        try {
            String update = "DELETE FROM player_blade_bringer WHERE player_uuid = ? AND blade_bringer = ?";
            GardensRPG.dbConnector.executeUpdate(update, uuid, bladeBringer);
        } catch (SQLException e) {
            GardensRPG.plugin.getLogger().log(Level.SEVERE, "Failed to remove player blade bringer entry", e);
            return false;
        }
        return true;
    }

    public boolean createEntry(String uuid, PlayerBladeBringerEntry playerBladeBringerEntry) {
        try {
            String update = "INSERT INTO player_blade_bringer (player_uuid, blade_bringer, hp, essence, blade, speed, attack_damage, defense, active, playtime) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            GardensRPG.dbConnector.executeUpdate(update, uuid, playerBladeBringerEntry.getBlade_bringer(), playerBladeBringerEntry.getHp(),
                    playerBladeBringerEntry.getEssence(), playerBladeBringerEntry.getBlade(), playerBladeBringerEntry.getSpeed(), playerBladeBringerEntry.getAttack_damage(),
                    playerBladeBringerEntry.getDefense(), playerBladeBringerEntry.isActive(), playerBladeBringerEntry.getPlaytime());
        } catch (SQLException e) {
            GardensRPG.plugin.getLogger().log(Level.SEVERE, "Failed to create player blade bringer entry", e);
            return false;
        }
        return true;
    }

    public boolean setEntry(String uuid, PlayerBladeBringerEntry playerBladeBringerEntry) {
        try {
            String update = "UPDATE player_blade_bringer SET blade_bringer = ?, hp = ?, essence = ?, blade = ?, speed = ?, " +
                        "attack_damage = ?, defense = ? WHERE player_uuid = ? AND blade_bringer = ?";
            GardensRPG.dbConnector.executeUpdate(update, playerBladeBringerEntry.getBlade_bringer(), playerBladeBringerEntry.getHp(),
                    playerBladeBringerEntry.getEssence(), playerBladeBringerEntry.getBlade(), playerBladeBringerEntry.getSpeed(), playerBladeBringerEntry.getAttack_damage(),
                    playerBladeBringerEntry.getDefense(), uuid);
        } catch (SQLException e) {
            GardensRPG.plugin.getLogger().log(Level.SEVERE, "Failed to set blade bringer entry", e);
            return false;
        }
        return true;
    }

    public boolean updateEntry(String uuid, String bladeBringerName, String columnName, Object columnValue) {
        try {
            String update = String.format("UPDATE player_blade_bringer SET %s = ? WHERE player_uuid = ? AND blade_bringer = ?", columnName);
            GardensRPG.dbConnector.executeUpdate(update, columnValue, uuid, bladeBringerName);
        } catch (SQLException e) {
            GardensRPG.plugin.getLogger().log(Level.SEVERE, "Failed to update blade bringer entry", e);
            return false;
        }
        return true;
    }
}
