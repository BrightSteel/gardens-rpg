package com.gardensmc.gardensrpg.gui;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.bladebringers.BladeBringer;
import de.themoep.inventorygui.GuiElementGroup;
import de.themoep.inventorygui.StaticGuiElement;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

public class BladeBringerMenu extends Menu {

    private static final String[] layout = new String[] {
            "aaaaaaaaa"
    };

    public BladeBringerMenu(Player viewer) {
        super(layout, "BladeBringers", viewer);
    }

    @Override
    protected void initializeElements() {
        GuiElementGroup group = new GuiElementGroup('a');
        Set<Map.Entry<String, BladeBringer>> bladeBringers = GardensRPG.bladeBringerHandler.getBladeBringers();
        for (Map.Entry<String, BladeBringer> entry : bladeBringers) {
            group.addElement(new StaticGuiElement(
                    'a',
                    new ItemStack(Material.NETHERITE_SWORD),
                    (e) -> selectBladeBringer(entry.getKey()),
                    entry.getKey()
            ));
        }
        gui.addElement(group);
    }

    private boolean selectBladeBringer(String bladeBringerName) {
        try {
            boolean success = GardensRPG.bladeBringerHandler.setBladeBringer(viewer, bladeBringerName);
            if (success) {
                viewer.sendMessage("Selected " + bladeBringerName + "!");
            } else {
                viewer.sendMessage("An internal error occurred, failed to select " + bladeBringerName);
            }
        } catch (Exception e) {
            GardensRPG.plugin.getLogger().log(Level.WARNING, "Failed to complete blade bringer request", e);
        }
        return true;
    }
}
