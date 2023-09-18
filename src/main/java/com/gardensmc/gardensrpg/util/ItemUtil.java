package com.gardensmc.gardensrpg.util;

import com.gardensmc.gardensfurniture.register.CustomRegister;
import com.gardensmc.gardensrpg.GardensRPG;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class ItemUtil {

    public static ItemStack getHeadBase64(String name, String value, int modelData) {
        ItemStack head = getHeadBase64(name, value);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        if (meta != null) {
            meta.setCustomModelData(modelData);
            head.setItemMeta(meta);
        }
        return head;
    }

    public static ItemStack getHeadBase64(String name, String value) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        if (meta != null) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), name);
            profile.getProperties().put("textures", new Property("textures", value));
            Field profileField;
            try {
                profileField = meta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(meta, profile);
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
            }
            head.setItemMeta(meta);
        }
        return head;
    }

    public static ItemStack getHead(UUID uuid) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        if (skullMeta != null) {
            skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
            head.setItemMeta(skullMeta);
        }
        return head;
    }

    public static ItemStack getBlankItem() {
        return getCustomItem("blank_item");
    }

    public static ItemStack getCustomItem(String id) {
        ItemStack stack = new ItemStack(Material.PAPER);
        ItemStack customItem = CustomRegister.INSTANCE.itemRegistry.buildItem(id);
        if (customItem != null) {
            stack = customItem;
        }
        return stack;
    }
}
