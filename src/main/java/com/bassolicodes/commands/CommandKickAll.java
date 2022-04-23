package com.bassolicodes.commands;

import com.bassolicodes.MinecraftCore;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandKickAll {

    FileConfiguration configuration = MinecraftCore.getInstance().getConfig();

    @Command(name = "kickall", permission = "core.kickall", target = CommandTarget.PLAYER)

    public void handleKickAll(Context<CommandSender> context) {
        for (Player allPlayers : Bukkit.getServer().getOnlinePlayers()) {
            if (!allPlayers.hasPermission("core.kickall")) {
                allPlayers.kickPlayer(String.format(configuration.getString("Message.Maintence_Kick").replace("&", "§"), allPlayers.getName()));
            }
        }
    }
}
