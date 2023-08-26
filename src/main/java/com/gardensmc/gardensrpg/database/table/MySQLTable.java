package com.gardensmc.gardensrpg.database.table;

import com.gardensmc.gardensrpg.GardensRPG;

public abstract class MySQLTable {

    public abstract String getPrepareStatement();

    public MySQLTable() {
        GardensRPG.mySQLPreparation.addStatement(getPrepareStatement());
    }
}
