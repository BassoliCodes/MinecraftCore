package com.bassolicodes.commands;

import com.bassolicodes.MinecraftCore;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandWebsite {

    FileConfiguration config = MinecraftCore.getInstance().getConfig();

    @Command(
            name = "site",
            aliases = {"website", "web"},
            target = CommandTarget.PLAYER
    )

    public void handleWebsite(Context<CommandSender> context) {
        val player = (Player) context.getSender();

        player.sendMessage(new String[] {
                "",
                config.getString("Message.Website").replace("&", "§"),
                ""
        });
    }

}
