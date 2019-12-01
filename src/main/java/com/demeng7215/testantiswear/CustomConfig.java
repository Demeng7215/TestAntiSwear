package com.demeng7215.testantiswear;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class CustomConfig {

    @Getter
    public File configFile;

    @Getter
    public FileConfiguration config;

    CustomConfig(String configName) throws Exception {

        final Plugin i = TestAntiSwearPlugin.getPlugin();

        configFile = new File(i.getDataFolder(), configName);
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            i.saveResource(configName, false);
        }

        config = new YamlConfiguration();
        config.load(configFile);
    }
}
