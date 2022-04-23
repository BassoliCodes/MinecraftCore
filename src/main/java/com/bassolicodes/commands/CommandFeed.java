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

public class CommandFeed {

    FileConfiguration configuration = MinecraftCore.getInstance().getConfig();

    @Command(
            name = "feed",
            permission = "core.feed",
            target = CommandTarget.PLAYER
    )
    public void handleFeed(Context<CommandSender> context, @Optional Player target) {
        val player = (Player) context.getSender();

        if (target == null) {
            if (player.getFoodLevel() == 20) {
                player.sendMessage(configuration.getString("Message.Feed_Max").replace("&", "§"));
            } else {
                player.setFoodLevel(20);
                player.sendMessage(configuration.getString("Message.Feed_Satiated").replace("&", "§"));
            }
        } else if (target.getFoodLevel() == 20) {
            player.sendMessage(configuration.getString("Message.Feed_Full_Player").replace("&", "§"));
        } else {
            target.setFoodLevel(20);
            player.sendMessage(String.format(configuration.getString("Message.Feed_Regenerated_Player").replace("&", "§"), target.getName()));
            target.sendMessage(String.format(configuration.getString("Message.Feed_Regenerated_By_Player").replace("&", "§"), player.getName()));
        }
    }
}
