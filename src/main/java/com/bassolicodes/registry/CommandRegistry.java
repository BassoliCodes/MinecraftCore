package com.bassolicodes.registry;

import com.bassolicodes.MinecraftCore;
import com.bassolicodes.commands.*;
import lombok.Data;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.command.message.MessageHolder;
import me.saiintbrisson.minecraft.command.message.MessageType;
import org.bukkit.configuration.file.FileConfiguration;

@Data(staticConstructor = "of")
public class CommandRegistry {

    private final MinecraftCore plugin;
    FileConfiguration config = MinecraftCore.getInstance().getConfig();

    public void register() {
        BukkitFrame bukkitFrame = new BukkitFrame(plugin);

        MessageHolder message = bukkitFrame.getMessageHolder();
        message.setMessage(MessageType.NO_PERMISSION, config.getString("Message.No_Permissions").replace("&", "§"));
        message.setMessage(MessageType.INCORRECT_TARGET, config.getString("Message.Incorect_Target").replace("&", "§"));
        message.setMessage(MessageType.INCORRECT_USAGE, "§cUtlize: /{usage}");
        bukkitFrame.registerCommands(
                new CommandFly(),
                new CommandGamemode(),
                new CommandFeed(),
                new CommandWebsite(),
                new CommandSystemStatus(),
                new CommandKickAll(),
                new CommandMaintence(),
                new CommandClearChat(),
                new CommandTrash(),
                new CommandProfile()
        );
    }
}
