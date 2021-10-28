package com.bassolicodes.commands;

import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class CommandTeleportHere {

    @Command(
            name = "tphere",
            permission = "core.tphere",
            target = CommandTarget.PLAYER
    )
    public void handleCommand(Context<CommandSender> context, Player target) {
        val player = (Player) context.getSender();

        if (target == player) {
            player.sendMessage("§cVocê não puxar si mesmo.");
        } else if (target == null) {
            player.sendMessage("§cEste jogador não está online.");
        } else {
            target.teleport(player.getLocation(), PlayerTeleportEvent.TeleportCause.COMMAND);
            target.sendMessage("§aVocê foi teleportado para " + player.getName() + ".");

            player.sendMessage("§aVocê teleportou " + target.getName() + " até você.");
        }
    }

}
