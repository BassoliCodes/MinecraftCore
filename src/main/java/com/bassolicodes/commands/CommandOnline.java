package com.bassolicodes.commands;

import com.bassolicodes.MinecraftCore;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandOnline {

    @Command(name = "online", permission = "core.online")
    public void handleOnline(Context<Player> context) {

        val player = context.getSender();
        FileConfiguration config = MinecraftCore.getInstance().getConfig();

        if (Bukkit.getServer().getOnlinePlayers().size() == 1) {
            player.sendMessage(new String[]{
                    "",
                    String.format(config.getString("Message.Just_You_Online").replace("&", "§"), Bukkit.getServer().getOnlinePlayers().size()),
                    ""
            });
            return;
        } else {
            player.sendMessage(new String[]{
                    "",
                    String.format(config.getString("Message.Players_Online").replace("&", "§"), Bukkit.getServer().getOnlinePlayers().size()),
                    ""
            });
            return;
        }
    }

}
