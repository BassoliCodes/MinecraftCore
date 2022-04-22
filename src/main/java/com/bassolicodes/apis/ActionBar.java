package com.bassolicodes.apis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.bassolicodes.MinecraftCore;
import com.bassolicodes.enums.Version;
import com.bassolicodes.utils.Reflection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBar {

    private static Method method;
    private static Object typeMessage;
    private static Constructor<?> chatConstructor;

    public static void sendActionBar(Player player, String message) {
        try {
            Object chatMessage = method.invoke(null, "{\"text\":\"" + message + "\"}");
            Object packet = chatConstructor.newInstance(chatMessage, typeMessage);
            Reflection.sendPacket(player, packet);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void broadcastActionBar(String message) {
        try
        {
            Object chatMessage = method.invoke(null, "{\"text\":\"" + message + "\"}");
            Object packet = chatConstructor.newInstance(chatMessage, typeMessage);
            for (Player player : Bukkit.getOnlinePlayers()) {
                Reflection.sendPacket(player, packet);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    static void load() {
        try
        {
            Class<?> typeMessageClass;
            Class<?> icbc = Reflection.getNMSClass("IChatBaseComponent");
            Class<?> ppoc = Reflection.getNMSClass("PacketPlayOutChat");

            if (icbc.getDeclaredClasses().length > 0) {
                method = icbc.getDeclaredClasses()[0].getMethod("a", String.class);
            } else {
                method = Reflection.getNMSClass("ChatSerializer").getMethod("a", String.class);
            }

            if (MinecraftCore.getVersion() == Version.v1_12 || MinecraftCore.isVerifyNewVersion()) {
                typeMessageClass = Reflection.getNMSClass("ChatMessageType");
                typeMessage = typeMessageClass.getEnumConstants()[2];
            } else {
                typeMessageClass = byte.class;
                typeMessage = (byte) 2;
            }

            chatConstructor = ppoc.getConstructor(icbc,  typeMessageClass);
        }
        catch (Throwable e) {}
    }
}