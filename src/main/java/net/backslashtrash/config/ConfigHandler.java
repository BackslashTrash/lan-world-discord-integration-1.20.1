package net.backslashtrash.config;


import com.electronwill.nightconfig.core.file.FileConfig;

import com.electronwill.nightconfig.core.io.WritingMode;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static net.backslashtrash.config.CommonConfig.botToken;
import static net.backslashtrash.config.CommonConfig.channelID;

public class ConfigHandler {
    public static final Path CONFIG_DIR = Paths.get("config");
    private static final File CONFIG_FILE = new File("config/lan-world-discord-integration.toml");
    public ConfigHandler() {

    }
    public void init() {
        try {
            if (Files.notExists(CONFIG_DIR)) {
                Files.createDirectories(CONFIG_DIR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileConfig fileConfig = FileConfig.builder(CONFIG_FILE).autosave().autoreload().writingMode(WritingMode.REPLACE).build();
        fileConfig.load();

        botToken = fileConfig.getOrElse("discord.bot_token","");
        CommonConfig.channelID = fileConfig.getOrElse("discord.channel_id",0L);

        fileConfig.set("discord.bot_token", botToken);
        fileConfig.set("discord.channel_id", channelID);
        fileConfig.save();

    }
}
