package com.gardensmc.gardensrpg.database;

import com.gardensmc.gardensrpg.GardensRPG;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class MySQLPreparation {

    private final List<String> prepareStatements;

    public MySQLPreparation() {
        this.prepareStatements = new ArrayList<>();
    }

    public void addStatement(String statement) {
        prepareStatements.add(statement);
    }

    public void executePrepareStatements() {
        try {
            for (String statement : prepareStatements) {
                GardensRPG.dbConnector.executeUpdate(statement);
            }
        } catch (SQLException e) {
            GardensRPG.plugin.getLogger()
                    .log(Level.SEVERE, "Failed to prepare MySQL Database, disabling plugin", e);
            GardensRPG.plugin.getServer().getPluginManager().disablePlugin(GardensRPG.plugin);
        }
    }
}
