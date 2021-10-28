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

public class CommandTeleport {
    @Command(
            name = "tp",
            aliases = {"teleport"},
            permission = "core.teleport",
            target = CommandTarget.PLAYER
    )
    public void handleCommand(Context<CommandSender> context, Player target) {
        val player = (Player) context.getSender();

        FileConfiguration config = MinecraftCore.getInstance().getConfig();

        if (target == player) {
            player.sendMessage(config.getString("Message.Teleport_To_Yourself").replace("&", "§"));
        } else if (target == null) {
            player.sendMessage(String.format(config.getString("Message.Teleport_Player_Offline").replace("&", "§"), target.getName()));
        } else {
            player.teleport(target.getLocation(), PlayerTeleportEvent.TeleportCause.COMMAND);
            player.sendMessage(String.format(config.getString("Message.Teleported_To_Player").replace("&", "§"), target.getName()));
        }
    }

}
