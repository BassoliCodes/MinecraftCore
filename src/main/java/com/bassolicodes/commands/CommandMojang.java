package com.bassolicodes.commands;

import com.bassolicodes.utils.TextLogger;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.shanerx.mojang.Mojang;

public class CommandMojang {

    @Command(
            name = "mojang",
            permission = "core.mojang"
    )
    public void handleClearChat(Context<CommandSender> context) {
        val player = (Player) context.getSender();

        player.sendMessage(new String[]{
                "&cAPI em desenvolvimento!"
        });
    }
}
