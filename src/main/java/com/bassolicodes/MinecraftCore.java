package com.bassolicodes;

import com.bassolicodes.enums.Version;
import lombok.val;
import lombok.Getter;
import com.bassolicodes.registry.CommandRegistry;
import com.bassolicodes.registry.EventRegistry;
import com.bassolicodes.utils.Config;
import com.bassolicodes.utils.TextLogger;
import com.google.common.base.Stopwatch;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class MinecraftCore extends JavaPlugin {

    public static MinecraftCore instance;
    private static Version version;
    private final TextLogger textLogger = new TextLogger();
    public static Config config;

    public static MinecraftCore getInstance() {
        return instance;
    }

    public static boolean isVeryNewVersion() {
        if (version == Version.v1_17)
            return true;
        if (version == Version.v1_16_5)
            return true;
        if (version == Version.v1_16_4)
            return true;
        if (version == Version.v1_16_3)
            return true;
        if (version == Version.v1_16_2)
            return true;
        if (version == Version.v1_16)
            return true;
        if (version == Version.v1_15)
            return true;
        if (version == Version.v1_14)
            return true;
        if (version == Version.v1_13)
            return true;
        return false;
    }



    @Override
    public void onEnable() {
        try {
            textLogger.info("Aguarde, iniciando o carregamento do plugin.");
            val loadTime = Stopwatch.createStarted();

            loadConfig();
            instance = this;
            allRecords();

            loadTime.stop();
            textLogger.info(String.format("Sucesso! O Plugin foi inicializado com sucesso. (%s)", loadTime));
        } catch (Exception e) {
            textLogger.error("Erro! O Plugin ocorreu problemas na inicialização.");
            e.getMessage();
        }
    }

    public void loadConfig() {
        try {
            val loadConfigTiming = Stopwatch.createStarted();

            textLogger.info("Aguarde, carregando configurações do plugin!");
            config = new Config(this, "config.yml");
            saveDefaultConfig();

            loadConfigTiming.stop();
            textLogger.info(String.format("Todas as informações de configuração foram lidas com sucesso! (%s)", loadConfigTiming));
        } catch (Throwable e) {
            textLogger.error("Ocorreu um erro ao carregar as configurações!");
            textLogger.error(e.getMessage());
        }
    }

    public void allRecords() {
        try {
            val allRecordsTiming = Stopwatch.createStarted();
            CommandRegistry.of(this).register();

            textLogger.info(String.format("Comandos registrados foram verificados... [1/2] (%s)", allRecordsTiming));
            allRecordsTiming.stop();

            val allEventsTiming = Stopwatch.createStarted();
            EventRegistry.of(this).register();
            allEventsTiming.stop();
            textLogger.info(String.format("Eventos registrados foram verificados... [2/2] (%s)", allEventsTiming));
        } catch (Exception e) {
            textLogger.error("Ocorreu um erro com o registro de eventos e comando, verifique!");
            textLogger.error(e.getMessage());
        }
    }

    @Override
    public void onDisable() {
        try {
            val disableTiming = Stopwatch.createStarted();

            saveConfig();

            disableTiming.stop();
            textLogger.info(String.format("O plugin foi encerrado com sucesso. (%s)", disableTiming));
        } catch (Exception e) {
            textLogger.error("Ocorreu um erro com o registro de eventos e comando, verifique!");
            textLogger.error(e.getMessage());
        }
    }

    public static Version getVersion() {
        return version;
    }

}
