package net.backslashtrash.config;

import discord4j.common.util.Snowflake;

public class SnowflakeConfig {
    public static boolean isValidChannelId(long channelId) {
        try {
            Snowflake.of(Snowflake.asString(channelId));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
