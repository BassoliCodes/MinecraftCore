package com.bassoli.registry;

import com.bassoli.Bassoli;
import com.bassoli.commands.CommandGamemode;
import lombok.Data;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.command.message.MessageHolder;
import me.saiintbrisson.minecraft.command.message.MessageType;

@Data(staticConstructor = "of")
public class CommandRegistry {

    private final Bassoli plugin;

    public void register() {
        BukkitFrame bukkitFrame = new BukkitFrame(plugin);

        MessageHolder message = bukkitFrame.getMessageHolder();
        message.setMessage(MessageType.INCORRECT_USAGE, "§c/{usage}");
        bukkitFrame.registerCommands(
                new CommandGamemode()
        );
    }
}
