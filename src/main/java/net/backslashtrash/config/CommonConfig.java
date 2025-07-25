package net.backslashtrash.config;

import com.electronwill.nightconfig.core.conversion.Path;
import com.electronwill.nightconfig.core.conversion.PreserveNotNull;

import java.io.File;

public class CommonConfig {

    @Path("discord.bot_ token")
    @PreserveNotNull
    public static String botToken ="";

    @Path("discord.channel_id")
    @PreserveNotNull
    public static long channelID =0L;
}
