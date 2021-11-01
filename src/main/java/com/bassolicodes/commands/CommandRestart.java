package com.bassolicodes.commands;

import com.bassolicodes.MinecraftCore;
import com.bassolicodes.utils.Title;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class CommandRestart {


    public static boolean restartActived = false;

    @Command(
            name = "reiniciar",
            permission = "core.restart",
            aliases = "restart"
    )
    public void handleRestart(Context<CommandSender> context) {

        val player = context.getSender();
        ArrayList<Player> restart = new ArrayList<Player>();
        FileConfiguration config = MinecraftCore.getInstance().getConfig();

        new BukkitRunnable() {

            int tempo = 120;

            @Override
            public void run() {
                tempo--;
                if (tempo == 119) {
                    Title.sendTitleToAll(config.getString("Informations.Server_Name").replace("&", "§") + "<nl>" + String.format(config.getString("Restart.Subtitle").replace("&", "§"), 120), 6, 6, 6);
                    Bukkit.broadcastMessage(String.format(config.getString("Restart.Message_In_Time").replace("&", "§"), 120));
                    return;
                }
                if (tempo == 59) {
                    Title.sendTitleToAll(config.getString("Informations.Server_Name").replace("&", "§") + "<nl>" + String.format(config.getString("Restart.Subtitle").replace("&", "§"), 120), 6, 6, 6);
                    Bukkit.broadcastMessage(String.format(config.getString("Restart.Message_In_Time").replace("&", "§"), 60));
                    return;
                }

                if (tempo == 9) {
                    restartActived = true;
                    Title.sendTitleToAll(config.getString("Informations.Server_Name").replace("&", "§") + "<nl>" + String.format(config.getString("Restart.Subtitle").replace("&", "§"), 120), 6, 6, 6);
                    Bukkit.broadcastMessage(String.format(config.getString("Restart.Message_In_Time").replace("&", "§"), 10));
                    return;
                }
                if (tempo == 1) {
                    for (Player on : Bukkit.getOnlinePlayers()) {
                        restart.remove(on);
                    }
                    Bukkit.broadcastMessage(config.getString("Restart.Message_Restart_Now").replace("&", "§"));
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
                    return;
                }

            }
        }.runTaskTimer(MinecraftCore.getInstance(), 0L, 20L);

    }

}
