package com.bassolicodes.commands;

import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class CommandTeleport {
    @Command(
            name = "tp",
            aliases = {"teleport"},
            permission = "core.teleport",
            target = CommandTarget.PLAYER
    )
    public void handleCommand(Context<CommandSender> context, Player target) {
        val player = (Player) context.getSender();

        if (target == player) {
            player.sendMessage("§cVocê não pode ir até si mesmo.");
        } else if (target == null) {
            player.sendMessage("§cEste jogador não está online.");
        } else {
            player.teleport(target.getLocation(), PlayerTeleportEvent.TeleportCause.COMMAND);
            player.sendMessage("§aVocê foi até a localização de " + target.getName() + ".");
        }
    }

}
