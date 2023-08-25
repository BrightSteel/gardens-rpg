package com.gardensmc.gardensrpg;

import com.gardensmc.gardensrpg.bladebringers.BladeBringerHandler;
import com.gardensmc.gardensrpg.database.MySQLConnector;
import com.gardensmc.gardensrpg.database.MySQLPreparation;
import com.gardensmc.gardensrpg.listener.PassiveAbilitiesListener;
import org.bukkit.plugin.java.JavaPlugin;

public class GardensRPG extends JavaPlugin {

    public static GardensRPG plugin;
    public static MySQLConnector dbConnector = new MySQLConnector();
    public static BladeBringerHandler bladeBringerHandler = new BladeBringerHandler();
    public static MySQLPreparation mySQLPreparation = new MySQLPreparation();

    @Override
    public void onEnable() {
        plugin = this;
        // register listeners
        this.getServer().getPluginManager().registerEvents(new PassiveAbilitiesListener(), this);
        // prepare MySQL Database
        mySQLPreparation.executePrepareStatements();
    }
}
