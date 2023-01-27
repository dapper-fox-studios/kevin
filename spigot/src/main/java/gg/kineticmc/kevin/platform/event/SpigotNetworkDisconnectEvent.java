package gg.kineticmc.kevin.platform.event;

import co.schemati.trevor.api.network.event.NetworkDisconnectEvent;
import co.schemati.trevor.api.network.payload.DisconnectPayload;

import java.util.UUID;

public class SpigotNetworkDisconnectEvent extends SpigotNetworkEvent implements NetworkDisconnectEvent {

    private final UUID uuid;
    private final long timestamp;

    public SpigotNetworkDisconnectEvent(DisconnectPayload payload) {
        this.uuid = payload.uuid();
        this.timestamp = payload.timestamp();
    }

    @Override
    public UUID uuid() {
        return this.uuid;
    }

    @Override
    public long timestamp() {
        return this.timestamp;
    }
}
