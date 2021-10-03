package com.bassoli.registry;

import com.bassoli.commands.CommandFly;
import lombok.Data;
import com.bassoli.Bassoli;
import com.bassoli.commands.CommandGamemode;
import com.bassoli.commands.CommandWebsite;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.command.message.MessageHolder;
import me.saiintbrisson.minecraft.command.message.MessageType;

@Data(staticConstructor = "of")
public class CommandRegistry {

    private final Bassoli plugin;

    public void register() {
        BukkitFrame bukkitFrame = new BukkitFrame(plugin);

        MessageHolder message = bukkitFrame.getMessageHolder();
        message.setMessage(MessageType.NO_PERMISSION, "§cVocê não tem permissão para isso.");
        message.setMessage(MessageType.INCORRECT_USAGE, "§cUtlize: /{usage}");
        bukkitFrame.registerCommands(
                new CommandFly(),
                new CommandGamemode(),
                new CommandWebsite()
        );
    }
}
