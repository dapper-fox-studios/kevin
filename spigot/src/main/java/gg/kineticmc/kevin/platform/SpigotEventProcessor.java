package gg.kineticmc.kevin.platform;

import co.schemati.trevor.api.network.event.*;
import co.schemati.trevor.api.network.payload.ConnectPayload;
import co.schemati.trevor.api.network.payload.DisconnectPayload;
import co.schemati.trevor.api.network.payload.NetworkPayload;
import co.schemati.trevor.api.network.payload.ServerChangePayload;
import gg.kineticmc.kevin.KevinSpigot;
import gg.kineticmc.kevin.platform.event.SpigotNetworkConnectEvent;
import gg.kineticmc.kevin.platform.event.SpigotNetworkDisconnectEvent;
import gg.kineticmc.kevin.platform.event.SpigotNetworkEvent;
import gg.kineticmc.kevin.platform.event.SpigotNetworkMessageEvent;

import java.util.concurrent.CompletableFuture;

public class SpigotEventProcessor implements EventProcessor {

    private final KevinSpigot plugin;

    public SpigotEventProcessor(KevinSpigot plugin) {
        this.plugin = plugin;
    }

    @Override
    public EventAction<SpigotNetworkConnectEvent> onConnect(ConnectPayload payload) {
        return this.wrap(new SpigotNetworkConnectEvent(payload));
    }

    @Override
    public EventAction<SpigotNetworkDisconnectEvent> onDisconnect(DisconnectPayload payload) {
        return this.wrap(new SpigotNetworkDisconnectEvent(payload));
    }

    @Override
    public <T extends NetworkServerChangeEvent> EventAction<T> onServerChange(ServerChangePayload payload) {
        return null;
    }

    @Override
    public EventAction<SpigotNetworkMessageEvent> onMessage(NetworkPayload payload) {
        return this.wrap(new SpigotNetworkMessageEvent(payload));
    }

    private <T extends SpigotNetworkEvent> EventAction<T> wrap(T event) {
        return new EventAction<>(event, e -> {
            CompletableFuture<T> future = new CompletableFuture<>();

            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
                plugin.getServer().getPluginManager().callEvent(e);

                future.complete(e);
            });

            return future;
        });
    }

}
