package com.bassolicodes.commands;

import com.bassolicodes.MinecraftCore;
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
                    Bukkit.broadcastMessage(String.format(config.getString("Restart.Message_In_120_Seconds").replace("&", "§"), 120));
                    return;
                }
                if (tempo == 59) {
                    Bukkit.broadcastMessage(String.format(config.getString("Restart.Message_In_120_Seconds").replace("&", "§"), 60));
                    return;
                }
                if (tempo == 9) {
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
