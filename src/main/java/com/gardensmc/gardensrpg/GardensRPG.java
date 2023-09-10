package com.gardensmc.gardensrpg;

import com.gardensmc.gardensrpg.ability.CastActiveAbilityHandler;
import com.gardensmc.gardensrpg.bladebringers.BladeBringerHandler;
import com.gardensmc.gardensrpg.cache.PlayerCooldownsCache;
import com.gardensmc.gardensrpg.cache.PlayerEntryCache;
import com.gardensmc.gardensrpg.cmd.CommandHandler;
import com.gardensmc.gardensrpg.database.MySQLConnector;
import com.gardensmc.gardensrpg.database.MySQLPreparation;
import com.gardensmc.gardensrpg.database.table.Tables;
import com.gardensmc.gardensrpg.listener.PassiveAbilitiesListener;
import com.gardensmc.gardensrpg.listener.PlayerListener;
import com.gardensmc.gardensrpg.placeholders.RPGExpansion;
import com.gardensmc.gardensrpg.scheduler.ScheduleHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class GardensRPG extends JavaPlugin {

    public static GardensRPG plugin;
    public static MySQLConnector dbConnector;
    public static BladeBringerHandler bladeBringerHandler;
    public static CastActiveAbilityHandler castActiveAbilityHandler;
    public static MySQLPreparation mySQLPreparation;

    // caches
    public static PlayerEntryCache playerEntryCache;
    public static PlayerCooldownsCache playerCooldownsCache;

    @Override
    public void onEnable() {
        // order matters for many of these, proceed cautiously...
        plugin = this;
        dbConnector = new MySQLConnector();
        mySQLPreparation = new MySQLPreparation();
        Tables.init();

        // prepare caches
        playerEntryCache = new PlayerEntryCache();
        playerCooldownsCache = new PlayerCooldownsCache();

        // prepare handlers
        bladeBringerHandler = new BladeBringerHandler();
        castActiveAbilityHandler = new CastActiveAbilityHandler();

        // register listeners
        this.getServer().getPluginManager().registerEvents(new PassiveAbilitiesListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        // prepare MySQL Database
        mySQLPreparation.executePrepareStatements();
        // register commands
        CommandHandler.registerCommands();
        // register PAPI expansion
        if (Bukkit.getPluginManager().getPlugin("PlaceHolderAPI") != null) {
            new RPGExpansion().register();
        }

        // schedule tasks
        new ScheduleHandler().scheduleAll();
    }
}
