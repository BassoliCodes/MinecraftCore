package com.bassolicodes.commands;

import com.bassolicodes.MinecraftCore;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandClearChat {

    FileConfiguration configuration = MinecraftCore.getInstance().getConfig();

    @Command(
            name = "limparchat",
            aliases = {"cc", "clearchat"},
            permission = "core.clearchat",
            target = CommandTarget.PLAYER
    )
    public void handleClearChat(Context<CommandSender> context) {

        val player = (Player) context.getSender();

        String clear = StringUtils.repeat(" §c \n §c ", 100);
        Bukkit.broadcastMessage(clear);
        Bukkit.broadcastMessage(String.format(configuration.getString("Message.Clear_Chat").replace("&", "§"), player.getName()));

    }

}
