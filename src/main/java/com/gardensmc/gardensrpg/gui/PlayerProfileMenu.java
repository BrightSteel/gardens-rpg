package com.gardensmc.gardensrpg.gui;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.database.entry.PlayerBladeBringerEntry;
import com.gardensmc.gardensrpg.util.ItemUtil;
import de.themoep.inventorygui.StaticGuiElement;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class PlayerProfileMenu extends Menu {

    private final ConfigurationSection guiConfig = GardensRPG.config.getConfigurationSection("gui");

    private static String[] layout = new String[] {
            "  aabccc ",
            "ddddeeeee",
            "ffffggggg",
            "hhhhiiiii",
            "j  klm   "
    };

    public PlayerProfileMenu(Player viewer) {
        super(layout, "&f", viewer);
    }

    @Override
    protected void initializeElements() {
        gui.addElement(new StaticGuiElement('b', ItemUtil.getHead(viewer.getUniqueId())));
        PlayerBladeBringerEntry playerBladeBringerEntry = GardensRPG.playerEntryCache.getActiveBladeBringer(viewer.getUniqueId());
        if (playerBladeBringerEntry != null) {
            String base64Head = getConfigValue(playerBladeBringerEntry.getBlade_bringer(), "head");
            gui.addElement(new StaticGuiElement('l', ItemUtil.getHeadBase64("", base64Head)));
        }
    }

    private String getConfigValue(String bladebringer, String item) {
        ConfigurationSection section = guiConfig.getConfigurationSection(bladebringer);
        if (section != null) {
            String value = section.getString(item);
            if (value != null) {
                return value;
            }
        }
        return "";
    }
}
