package com.gardensmc.gardensrpg;

import com.gardensmc.gardensrpg.bladebringers.BladeBringerHandler;
import com.gardensmc.gardensrpg.listener.PassiveAbilitiesListener;
import org.bukkit.plugin.java.JavaPlugin;

public class GardensRPG extends JavaPlugin {

    public BladeBringerHandler bladeBringerHandler = new BladeBringerHandler();

    @Override
    public void onEnable() {
        // register listeners
        this.getServer().getPluginManager().registerEvents(new PassiveAbilitiesListener(), this);
    }
}
