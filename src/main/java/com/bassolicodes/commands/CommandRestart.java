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

    @Command(name = "reiniciar", permission = "core.restart", aliases = "restart")
    public void handleRestart(Context<CommandSender> context) {

        val player = context.getSender();
         ArrayList<Player> restart = new ArrayList<Player>();

        new BukkitRunnable() {

            int tempo = 120;

            @Override
            public void run() {
                tempo--;
                if (tempo == 119) {
                    Bukkit.broadcastMessage("§e§lREINICIO: §eRestam 120 segundos para o server ser reinicado.");
                    return;
                }
                if (tempo == 59) {
                    Bukkit.broadcastMessage("§e§lREINICIO: §eRestam 60 segundos para o server ser reinicado.");
                    return;
                }
                if (tempo == 9) {
                    Bukkit.broadcastMessage("§e§lREINICIO: §eRestam 10 segundos para o server ser reinicado.");
                    return;
                }
                if (tempo == 1) {
                    for (Player on : Bukkit.getOnlinePlayers()) {
                        restart.remove(on);
                    }

                    Bukkit.broadcastMessage("§e§lREINICIO: §eReiniciando o servídor.");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
                    return;
                }

            }
        }.runTaskTimer(MinecraftCore.getInstance(), 0L, 20L);

    }

}
