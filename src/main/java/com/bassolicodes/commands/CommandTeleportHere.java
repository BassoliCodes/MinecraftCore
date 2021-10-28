package com.bassolicodes.commands;

import com.bassolicodes.MinecraftCore;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class CommandTeleportHere {

    @Command(
            name = "tphere",
            permission = "core.tphere",
            target = CommandTarget.PLAYER
    )
    public void handleCommand(Context<CommandSender> context, Player target) {

        FileConfiguration config = MinecraftCore.getInstance().getConfig();
        val player = (Player) context.getSender();

        if (target == player) {
            player.sendMessage(config.getString("Message.Teleport_To_Yourself").replace("&", "§"));
        } else if (target == null) {
            player.sendMessage("§cEste jogador não está online.");
        } else {
            target.teleport(player.getLocation(), PlayerTeleportEvent.TeleportCause.COMMAND);
            target.sendMessage(String.format(config.getString("Message.Teleported_To_Player").replace("&", "§"), player.getName()));

            player.sendMessage(String.format(config.getString("Message.Teleported_To_You").replace("&", "§"), target.getName()));
        }
    }

}
