package gg.kineticmc.kevin.platform.event;

import co.schemati.trevor.api.network.event.NetworkConnectEvent;
import co.schemati.trevor.api.network.payload.ConnectPayload;

import java.util.UUID;

public class SpigotNetworkConnectEvent extends SpigotNetworkEvent implements NetworkConnectEvent {

    private final UUID uuid;
    private final String name;
    private final String address;

    public SpigotNetworkConnectEvent(ConnectPayload payload) {
        this.uuid = payload.uuid();
        this.name = payload.name();
        this.address = payload.address();
    }

    @Override
    public UUID uuid() {
        return this.uuid;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String address() {
        return this.address;
    }
}
