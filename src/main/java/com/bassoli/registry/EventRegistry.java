package com.bassoli.registry;

import com.bassoli.Core;
import com.bassoli.events.PlayerEvents;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

@Data(staticConstructor = "of")
public class EventRegistry {

    private final Core plugin;

    public void register() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(
                new PlayerEvents(),
                plugin
        );

    }

}
