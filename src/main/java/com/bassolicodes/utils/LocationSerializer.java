package com.bassolicodes.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

public class LocationSerializer {

    public static String getSerializedLocation(Location location) { //Converts location -> String
        return location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getWorld().getUID();
    }

    public static Location getDeserializedLocation(String serializedLocation) {
        String [] parts = serializedLocation.split(";");

        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        double z = Double.parseDouble(parts[2]);

        UUID uuid = UUID.fromString(parts[3]);
        World world = Bukkit.getServer().getWorld(uuid);

        return new Location(world, x, y, z);
    }
}
