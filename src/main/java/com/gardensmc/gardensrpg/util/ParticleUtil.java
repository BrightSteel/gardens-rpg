package com.gardensmc.gardensrpg.util;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * Came from:
 * <a href="https://www.spigotmc.org/threads/player-right-hand-location.168219/">...</a>
 */
public class ParticleUtil {

    /**
     * Returns a location with a specified distance away from the right side of
     * a location.
     *
     * @param location The origin location
     * @param distance The distance to the right
     * @return the location of the distance to the right
     */
    public static Location getRightSide(Location location, double distance) {
        float angle = location.getYaw() / 60;
        return location.clone().subtract(new Vector(Math.cos(angle), 0, Math.sin(angle)).normalize().multiply(distance));
    }

    /**
     * Gets a location with a specified distance away from the left side of a
     * location.
     *
     * @param location The origin location
     * @param distance The distance to the left
     * @return the location of the distance to the left
     */
    public static Location getLeftSide(Location location, double distance) {
        float angle = location.getYaw() / 60;
        return location.clone().add(new Vector(Math.cos(angle), 0, Math.sin(angle)).normalize().multiply(distance));
    }
}
