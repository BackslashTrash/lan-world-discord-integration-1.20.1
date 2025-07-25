package net.backslashtrash;


import discord4j.common.util.Snowflake;
import discord4j.common.util.TokenUtil;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import net.backslashtrash.config.CommonConfig;
import net.backslashtrash.config.ConfigHandler;
import net.backslashtrash.config.SnowflakeConfig;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import static net.backslashtrash.config.CommonConfig.botToken;
import static net.backslashtrash.config.CommonConfig.channelID;


public class LanWorldDiscordIntegration implements ModInitializer {
	public static final String MOD_ID = "lan-world-discord-integration";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public GatewayDiscordClient discordClient;
	public ConfigHandler configHandler = new ConfigHandler();

	private static MinecraftServer minecraftServer;
	static {
		ServerLifecycleEvents.SERVER_STARTED.register(s -> minecraftServer = s);
	}


	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Lan World Discord Integration");
		configHandler.init();
		ServerMessageEvents.CHAT_MESSAGE.register((message, sender, params) -> {
			if (discordClient != null) {
				String playerName = sender.getName().getString();
				String content = message.getContent().getString();
				sendToDiscord("**" + playerName + "**: " + content);
			}
		});
		if (loadConfig()){
			ServerLifecycleEvents.SERVER_STARTED.register(this::startBot);
		} else {
			LOGGER.error("Bot not started due to invalid config.");
		}
		ServerLifecycleEvents.SERVER_STOPPED.register(server -> stopBot());

	}

	private boolean loadConfig() {

		if (botToken == null || botToken.isEmpty() || channelID == 0L) {
			LOGGER.error("Bot token or channel ID is missing!");
			return false;
		}
		try {
			TokenUtil.getSelfId(botToken);
		} catch (IllegalArgumentException e) {
			LOGGER.info("Invalid Discord Token, unable to proceed");
			return false;
		}

		try {
			SnowflakeConfig.isValidChannelId(CommonConfig.channelID);
		} catch (IllegalArgumentException e) {
			LOGGER.info("Invalid Channel ID, unable to proceed");
			return false;
		}
		return true;
	}

	private void startBot(MinecraftServer server) {
		stopBot();

		discordClient = DiscordClientBuilder.create(botToken).build().login().block();
		if (discordClient == null) {
			LOGGER.error("Failed to connect to Discord!");
			return;
		}

		LOGGER.info("Connected to Discord.");
		discordClient.getEventDispatcher().on(MessageCreateEvent.class)
				.filter(event -> event.getMessage().getChannelId().asLong() == channelID)
				.filter(event -> event.getMessage().getAuthor().map(user -> !user.isBot()).orElse(false))
				.subscribe(event -> {
					String content = event.getMessage().getContent();

					for (var user : event.getMessage().getUserMentions()) {
						String mentionId = "<@" + user.getId().asString() + ">";
						content = content.replace(mentionId, "@" + user.getUsername());
					}

					for (var role : event.getMessage().getRoleMentionIds()) {
						String mentionId = "<@&" + role.asString() + ">";
						content = content.replace(mentionId, "@role");
					}

					content = content.replace("@everyone", "§e@everyone§r");
					content = content.replace("@here", "§e@here§r");

					var member = event.getMember().orElse(null);
					var message = event.getMessage();
					String displayName = (member != null) ? member.getDisplayName() : message.getAuthor().map(User::getUsername).orElse("Unknown");
					String finalMessage = "[Discord] " + displayName + ": " + content;

					server.execute(() -> {
						server.getPlayerManager().broadcast(Text.literal(finalMessage), false);
					});
				});
	}

	private void sendToDiscord(String content) {
		if (discordClient != null) {
			Mono<MessageChannel> channelMono = discordClient.getChannelById(Snowflake.of(channelID))
					.ofType(MessageChannel.class);
			channelMono.flatMap(channel -> channel.createMessage(content)).subscribe();
		}
	}

	private void stopBot() {
		if (discordClient != null) {
			discordClient.logout().block();
			discordClient = null;
			LOGGER.info("Bot disconnected.");
		}
	}
}