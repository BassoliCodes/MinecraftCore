package com.bassolicodes.events;

import lombok.val;
import com.bassolicodes.MinecraftCore;
import com.bassolicodes.commands.CommandMaintence;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityPortalExitEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ServerAndPlayersEvents implements Listener {

    FileConfiguration config = MinecraftCore.getInstance().getConfig();

    @EventHandler
    public void onMaintence(PlayerJoinEvent event) {
        val player = event.getPlayer();

        event.setJoinMessage(null);

        player.sendMessage(new String[]
                {
                        "",
                        "    §lBEM VINDO AO SERVIDOR!",
                        "",
                        " &7Olá " + player.getName() + ", seja bem vindo ao nosso servidor.",
                        ""
                });

        if (CommandMaintence.maintenceStatus) {
            if (!player.hasPermission("core.manutencao")) {
                player.kickPlayer("§cVocê não pode entrar com o servidor em manutenção!");
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
    }

    @EventHandler
    public void deathOnJoin(EntityPortalEnterEvent event) {
        if (event.getEntity().isDead()) {
            event.getEntity().remove();
        }
    }

    @EventHandler
    public void deathOnQuit(EntityPortalExitEvent event) {
        if (event.getEntity().isDead()) {
            event.getEntity().remove();
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onRain(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.setCancelled(true);
        }
    }

}
