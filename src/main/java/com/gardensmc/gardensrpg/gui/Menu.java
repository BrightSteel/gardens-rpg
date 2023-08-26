package com.gardensmc.gardensrpg.gui;

import com.gardensmc.gardensrpg.GardensRPG;
import de.themoep.inventorygui.InventoryGui;
import org.bukkit.entity.Player;

public class Menu {

    protected String[] layout;
    protected Player viewer;

    protected InventoryGui gui;

    public Menu(String[] layout, String title, Player viewer) {
        this.viewer = viewer;
        this.gui = new InventoryGui(GardensRPG.plugin, viewer, title, layout);
        initializeElements();
    }

    protected void initializeElements() {}

    public void open() { gui.show(viewer); }
}
