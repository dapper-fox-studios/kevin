package gg.kineticmc.kevin.platform.event;

import co.schemati.trevor.api.network.event.NetworkEvent;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SpigotNetworkEvent extends Event implements NetworkEvent {

    private static final HandlerList handlerList = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
