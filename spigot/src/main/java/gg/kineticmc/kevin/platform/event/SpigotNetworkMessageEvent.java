package gg.kineticmc.kevin.platform.event;

import co.schemati.trevor.api.network.event.NetworkIntercomEvent;
import co.schemati.trevor.api.network.payload.NetworkPayload;

public class SpigotNetworkMessageEvent extends SpigotNetworkEvent implements NetworkIntercomEvent {

    private final NetworkPayload<?> payload;

    public SpigotNetworkMessageEvent(NetworkPayload<?> payload) {
        this.payload = payload;
    }

    @Override
    public NetworkPayload<?> payload() {
        return this.payload;
    }
}
