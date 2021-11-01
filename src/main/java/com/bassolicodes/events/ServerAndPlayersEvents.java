package com.bassolicodes.events;

import com.bassolicodes.commands.CommandRestart;
import com.bassolicodes.utils.Config;
import com.bassolicodes.utils.TitleUtils;
import lombok.val;
import com.bassolicodes.MinecraftCore;
import com.bassolicodes.commands.CommandMaintence;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityPortalExitEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ServerAndPlayersEvents implements Listener {

    FileConfiguration config = MinecraftCore.getInstance().getConfig();

    @EventHandler
    public void onMaintence(PlayerJoinEvent event) {
        val player = event.getPlayer();

        event.setJoinMessage(null);

        TitleUtils.sendTitle(
                player,
                config.getString("Message.Join_server.title").replace("&", "§") +
                        "<nl>" +
                        config.getString("Message.Join_server.subtitle").replace("&", "§"),
                6, 6, 6
        );

        player.playSound(player.getLocation(), Sound.CAT_MEOW, 1f, 1f);
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

    @EventHandler
    public void onRestartBlockItems(PlayerInteractEvent event) {
        val player = (Player) event.getPlayer();
        if (CommandRestart.restartActived) {
            event.setCancelled(true);
            config.getStringList("Restart.Message_On_Interact_Items").forEach(line -> player.sendMessage(line.replace("&", "§")));
            player.playSound(
                    player.getLocation(),
                    Sound.CAT_MEOW,
                    2.0F, 2.0F
            );
        }
    }

    @EventHandler
    public void onRestartBlockCommands(PlayerCommandPreprocessEvent event) {
        val player = (Player) event.getPlayer();
        if (CommandRestart.restartActived) {
            event.setCancelled(true);
            config.getStringList("Restart.Message_On_Interact_Commands").forEach(line -> player.sendMessage(line.replace("&", "§")));
            player.playSound(
                    player.getLocation(),
                    Sound.CAT_MEOW,
                    2.0F, 2.0F
            );
        }
    }

}
