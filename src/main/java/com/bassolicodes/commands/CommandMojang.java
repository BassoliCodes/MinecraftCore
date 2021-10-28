package com.bassolicodes.commands;

import com.bassolicodes.utils.TextLogger;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.shanerx.mojang.Mojang;

public class CommandMojang {

    Mojang mojang = new Mojang().connect();
    private final TextLogger textLogger = new TextLogger();

    @Command(
            name = "mojang",
            permission = "core.mojang"
    )
    public void handleClearChat(Context<CommandSender> context) {
        val player = (Player) context.getSender();

        if (mojang.getStatus(Mojang.ServiceType.AUTHSERVER_MOJANG_COM) != Mojang.ServiceStatus.GREEN) {
            textLogger.error("O Auth Server dos servidores Mojang não está disponível no momento.");
            return;
        }
    }
}
