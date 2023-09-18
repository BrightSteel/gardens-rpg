package com.gardensmc.gardensrpg.gui;

import com.gardensmc.gardensrpg.GardensRPG;
import com.gardensmc.gardensrpg.bladebringers.BladeBringer;
import com.gardensmc.gardensrpg.util.ItemUtil;
import de.themoep.inventorygui.GuiElementGroup;
import de.themoep.inventorygui.StaticGuiElement;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class BladeBringerMenu extends Menu {

    private int selected = 0; // corresponds to index of currently viewed bladebringer
    private int page = 0;
    private final List<Map.Entry<String, BladeBringer>> bladeBringers;
    private final ConfigurationSection guiConfig = GardensRPG.config.getConfigurationSection("gui");

    private static final String[] layout = new String[] {
            "         ",
            "         ",
            "         ",
            "         ",
            "baaaaaaan",
            "    d    "
    };

    public BladeBringerMenu(Player viewer) {
        super(layout, "&f", viewer);
        this.bladeBringers = GardensRPG.bladeBringerHandler.getBladeBringers().stream().toList();
        initElements();
    }

    @Override
    public void open() {
        super.open();
        setTitle();
    }

    private void initElements() {
        GuiElementGroup grp = new GuiElementGroup('a');
        int i = 0;
        for (Map.Entry<String, BladeBringer> bb : bladeBringers) {
            String bladebringer = bb.getKey();
            String base64Head = getConfigValue(bladebringer, "head");
            int finalI = i;
            grp.addElement(new StaticGuiElement('a', ItemUtil.getHeadBase64("", base64Head), (c) -> updateSelected(finalI), bladebringer));
            i++;
        }
        gui.addElement(grp);

        gui.addElement(new StaticGuiElement('d', ItemUtil.getBlankItem(), c -> onClickDone(), "Confirm"));
    }

    private boolean updateSelected(int i) {
        selected = i;
        setTitle();
        return true;
    }

    private void setTitle() {
        String bladebringer = bladeBringers.get(selected).getKey();
        String currImage = getConfigValue(bladebringer, "image");
        String mainTitle = guiConfig.getString("title");
        viewer.getOpenInventory().setTitle(ChatColor.translateAlternateColorCodes('&', mainTitle + currImage));
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

    private boolean onClickDone() {
        if (selectBladeBringer(bladeBringers.get(selected).getKey())) {
            gui.close();
        }
        return true;
    }

    private boolean selectBladeBringer(String bladeBringerName) {
        try {
            boolean success = GardensRPG.bladeBringerHandler.createBladeBringer(viewer, bladeBringerName);
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
