package com.gardensmc.gardensrpg;

import com.gardensmc.gardensrpg.bladebringers.BladeBringerHandler;
import com.gardensmc.gardensrpg.cmd.CommandHandler;
import com.gardensmc.gardensrpg.database.MySQLConnector;
import com.gardensmc.gardensrpg.database.MySQLPreparation;
import com.gardensmc.gardensrpg.database.table.Tables;
import com.gardensmc.gardensrpg.listener.PassiveAbilitiesListener;
import org.bukkit.plugin.java.JavaPlugin;

public class GardensRPG extends JavaPlugin {

    public static GardensRPG plugin;
    public static MySQLConnector dbConnector;
    public static BladeBringerHandler bladeBringerHandler;
    public static MySQLPreparation mySQLPreparation;

    @Override
    public void onEnable() {
        plugin = this;
        dbConnector = new MySQLConnector();
        bladeBringerHandler = new BladeBringerHandler();
        mySQLPreparation = new MySQLPreparation();
        Tables.init();
        // register listeners
        this.getServer().getPluginManager().registerEvents(new PassiveAbilitiesListener(), this);
        // prepare MySQL Database
        mySQLPreparation.executePrepareStatements();
        // register commands
        CommandHandler.registerCommands();
    }
}
