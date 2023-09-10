package com.gardensmc.gardensrpg.database.table;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.database.entry.PlayerEntry;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class PlayerTable extends MySQLTable {

    private static final String prepareStatement = """
            CREATE TABLE IF NOT EXISTS player(
                uuid varchar(36) NOT NULL,
                bladeBringer varchar(50) NOT NULL,
                health integer NOT NULL,
                mana integer NOT NULL,
                power integer NOT NULL,
                speed double NOT NULL,
                damage double NOT NULL,
                defense double NOT NULL,
                PRIMARY KEY (uuid)
            )
            """;

    @Override
    public String getPrepareStatement() {
        return prepareStatement;
    }

    public PlayerEntry getPlayerEntry(String uuid) {
        try {
            String query = "SELECT * FROM player WHERE uuid = ?";
            PlayerEntry playerEntry = new PlayerEntry();
            return (PlayerEntry) GardensRPG.dbConnector.querySingleton(query, playerEntry, uuid);
        } catch (SQLException e) {
            GardensRPG.plugin.getLogger().log(Level.WARNING, "Failed to query Player Entry", e);
            return null;
        }
    }

    public boolean setOrCreatePlayerEntry(String uuid, PlayerEntry playerEntry) {
        try {
            String update;
            if (getPlayerEntry(uuid) != null) {
                update = "UPDATE player SET bladeBringer = ?, health = ?, mana = ?, power = ?, speed = ?, " +
                        "damage = ?, defense = ? WHERE uuid = ?";
                GardensRPG.dbConnector.executeUpdate(update, playerEntry.getBladeBringer(), playerEntry.getHealth(),
                        playerEntry.getMana(), playerEntry.getPower(), playerEntry.getSpeed(), playerEntry.getDamage(),
                        playerEntry.getDefense(), uuid);
            } else {
                update = "INSERT INTO player (uuid, bladeBringer, health, mana, power, speed, damage, defense) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                GardensRPG.dbConnector.executeUpdate(update, uuid, playerEntry.getBladeBringer(), playerEntry.getHealth(),
                        playerEntry.getMana(), playerEntry.getPower(), playerEntry.getSpeed(), playerEntry.getDamage(),
                        playerEntry.getDefense());
            }
        } catch (SQLException e) {
            GardensRPG.plugin.getLogger().log(Level.SEVERE, "Failed to setOrCreatePlayerEntry", e);
            return false;
        }

        return true;
    }
}
