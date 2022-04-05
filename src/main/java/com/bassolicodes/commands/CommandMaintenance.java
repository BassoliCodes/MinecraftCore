package com.bassolicodes.commands;

import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandMaintenance {

    public static boolean maintenanceStatus = false;

    @Command(
            name = "manutencao",
            aliases = "maintence",
            permission = "core.manutencao"
    )

    public void handleMaintenance(Context<Player> context) {
        val player = (Player) context.getSender();

        for (Player allPlayers : Bukkit.getServer().getOnlinePlayers()) {

            if (!maintenanceStatus) {

                maintenanceStatus = true;

                if (!allPlayers.hasPermission("core.manutencao")) {
                    allPlayers.kickPlayer("§cServidor entrou em manutenção, avisaremos a volta no discord.");
                } else if (maintenanceStatus) {
                    maintenanceStatus = false;
                    player.sendMessage("§cO servidor está em manutenção!");
                }
            }
        }
    }
}
