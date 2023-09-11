package com.gardensmc.gardensrpg.gui;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.bladebringers.BladeBringer;
import de.themoep.inventorygui.GuiElementGroup;
import de.themoep.inventorygui.StaticGuiElement;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

public class BladeBringerMenu extends Menu {

    private int currIndex = 0; // corresponds to index of currently viewed bladebringer
    private final List<Map.Entry<String, BladeBringer>> bladeBringers;
    private final ConfigurationSection guiConfig = GardensRPG.config.getConfigurationSection("gui");

    private static final String[] layout = new String[] {
            "aaaaaaaaa",
            "aaaaaaaaa",
            "aaaaaaaaa"
    };

    public BladeBringerMenu(Player viewer) {
        super(layout, "&f", viewer);
        this.bladeBringers = GardensRPG.bladeBringerHandler.getBladeBringers().stream().toList();
        setTitle();
    }

    @Override
    protected void initializeElements() {}

    private void setTitle() {
        int prevIndex = (currIndex != 0) ? currIndex - 1 : bladeBringers.size() - 1;
        int nextIndex = (currIndex != bladeBringers.size() - 1) ? currIndex + 1 : 0;

        String prevImage = getGuiImage(
                bladeBringers.get(prevIndex).getKey(),
                "blank"
        );
        String nextImage = getGuiImage(
                bladeBringers.get(nextIndex).getKey(),
                "blank"
        );
        String currImage = getGuiImage(
                bladeBringers.get(currIndex).getKey(),
                "normal"
        );
        viewer.getOpenInventory().setTitle(String.format("%s%s%s", prevImage, currImage, nextImage));
    }

    private String getGuiImage(String bladeBringer, String type) {
        ConfigurationSection bladeBringerSection = guiConfig.getConfigurationSection(bladeBringer);
        if (bladeBringerSection != null) {
            return bladeBringerSection.getString(type);
        } else {
            return "";
        }
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
