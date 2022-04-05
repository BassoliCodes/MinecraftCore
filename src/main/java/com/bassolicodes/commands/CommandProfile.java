package com.bassolicodes.commands;

import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;

public class CommandProfile {

    private Connection connection;

    @Command(name = "perfil", aliases = {"profile", "myinfo"}, permission = "core.perfil")

    public void handleProfile(Context<CommandSender> context, @Optional Player target) {
        val player = (Player) context.getSender();

        if (target == null) {
            player.sendMessage(new String[]{"", " §lSUAS INFORMAÇÕES", "", " §aNick: §7" + player.getName(), " §aSua fome: §7" + player.getFoodLevel(), " §aEstá voando: §7" + (player.isFlying() ? "Sim" : "Não"), ""});
        } else {
            player.sendMessage(new String[]{"", " §lINFORMAÇÕES DE " + target.getName().toUpperCase(), "", " §aNick: §7" + target.getName(), " §aSua fome: §7" + target.getFoodLevel(), " §aEstá voando: §7" + (target.isFlying() ? "Sim" : "Não"), ""});
        }
    }

    public void getPlayerInformations(String uuid) {


    }

}
