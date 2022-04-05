package com.bassolicodes.apis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.bassolicodes.utils.Reflection;
import com.bassolicodes.utils.TextLogger;
import org.bukkit.entity.Player;

public class Ping {

    private static Method getHandle;
    private static TextLogger textLogger = new TextLogger();
    private static Field ping;

    public static String getPlayerPing(Player player) {
        try {
            Object entityPlayer = getHandle.invoke(player);
            return String.valueOf(ping.get(entityPlayer));
        } catch (Throwable e) {
            return "Indisponível";
        }
    }

    static void load() {
        try {
            Class<?> craftPlayerClass = Reflection.getOBClass("entity.CraftPlayer");
            Class<?> entityPlayerClass = Reflection.getNMSClass("EntityPlayer");
            getHandle = craftPlayerClass.getMethod("getHandle");
            ping = entityPlayerClass.getField("ping");
        } catch (Throwable e) {
            textLogger.error("Ocorreu um erro ao verificar o ping do jogador!");
            e.getMessage();
        }
    }

}