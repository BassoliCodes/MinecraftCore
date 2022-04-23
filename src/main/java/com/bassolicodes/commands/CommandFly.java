package com.bassolicodes.commands;

import com.bassolicodes.MinecraftCore;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandFly {

    FileConfiguration configuration = MinecraftCore.config.getConfig();

    @Command(name = "fly", permission = "core.fly", target = CommandTarget.PLAYER)
    public void handleFly(Context<CommandSender> context, @Optional Player target) {
        val player = (Player) context.getSender();

        if (player == target) {
            player.sendMessage(String.format(configuration.getString("Message.Verification_Fly_Player").replace("&", "§")));
            return;
        }
        if (target == null) {
            if (!player.getAllowFlight()) {
                player.setAllowFlight(true);
                player.sendMessage(configuration.getString("Message.Fly_Enabled").replace("&", "§"));
            } else {
                player.setAllowFlight(false);
                player.sendMessage(configuration.getString("Message.Fly_Disabled").replace("&", "§"));
            }
        } else if (!target.getAllowFlight()) {
            player.setAllowFlight(true);
            player.sendMessage(String.format(configuration.getString("Message.Fly_Enabled_Target").replace("&", "§"), target.getName()));
            target.sendMessage(String.format(configuration.getString("Message.Fly_Enabled_Player"), player.getName()));
        } else {
            player.setAllowFlight(false);
            player.sendMessage(String.format(configuration.getString("Message.Fly_Disabled_Target").replace("&", "§"), target.getName()));
            target.sendMessage(String.format(configuration.getString("Message.Fly_Disabled_Player"), player.getName()));
        }
    }
}
