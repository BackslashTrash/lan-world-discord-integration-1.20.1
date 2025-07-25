package net.backslashtrash.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;

public class LifecycleEvents {
    private static MinecraftServer server;

    static {
        ServerLifecycleEvents.SERVER_STARTED.register(s -> server = s);
    }

    public static void sendToAllPlayers(String message) {
        if (server != null) {
            server.getPlayerManager().broadcast(Text.of(message), false);
        }
    }
}
