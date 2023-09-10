package com.gardensmc.gardensrpg.database;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.database.entry.MySQLEntry;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.ConfigurationSection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@SuppressWarnings("DuplicatedCode")
public class MySQLConnector {

    public HikariConfig hikariConfig = new HikariConfig();
    private final HikariDataSource dataSource;

    public MySQLConnector() {
        ConfigurationSection configSection = Objects.requireNonNull(GardensRPG.plugin.getConfig().getConfigurationSection("database"));
        String db = configSection.getString("databaseName");
        String host = configSection.getString("host");
        String port = configSection.getString("port");
        String username = configSection.getString("username");
        String password = configSection.getString("password");

        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(3);
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db;
        hikariConfig.setJdbcUrl(url);
        dataSource = new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void executeUpdate(String string, Object... obj) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(string);
        for(int i = 0; i < obj.length; i++) {
            ps.setObject(i + 1, obj[i]);
        }
        ps.executeUpdate();
        ps.close();
        connection.close();
    }

    public MySQLEntry querySingleton(String query, MySQLEntry mySQLEntry, Object... obj) throws SQLException {
        Connection connection = GardensRPG.dbConnector.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        for(int i = 0; i < obj.length; i++) {
            ps.setObject(i + 1, obj[i]);
        }
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            mySQLEntry.populate(rs);
        } else {
            mySQLEntry = null;
        }

        rs.close();
        ps.close();
        connection.close();
        return mySQLEntry;
    }

    public Collection<MySQLEntry> queryCollection(String query, MySQLEntry mySQLEntry, Object... obj) throws SQLException {
        Collection<MySQLEntry> collection = new ArrayList<>();
        Connection connection = GardensRPG.dbConnector.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        for(int i = 0; i < obj.length; i++) {
            ps.setObject(i + 1, obj[i]);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            MySQLEntry entry = mySQLEntry.clone();
            entry.populate(rs);
            collection.add(entry);
        }

        rs.close();
        ps.close();
        connection.close();
        return collection;
    }
}
