package gg.kineticmc.kevin.platform;

import co.schemati.trevor.api.network.event.EventProcessor;
import co.schemati.trevor.api.util.Strings;
import co.schemati.trevor.common.platform.AbstractPlatformBase;
import gg.kineticmc.kevin.KevinSpigot;

public class SpigotPlatform extends AbstractPlatformBase {

    private final KevinSpigot plugin;

    private SpigotEventProcessor eventProcessor;

    public SpigotPlatform(KevinSpigot plugin) {
        super(plugin.getDataFolder());

        this.plugin = plugin;
    }

    @Override
    public boolean init() {
        if (!super.init()) {
            return false;
        }

        this.eventProcessor = new SpigotEventProcessor(plugin);

        return true;
    }

    @Override
    public EventProcessor getEventProcessor() {
        return this.eventProcessor;
    }

    @Override
    public boolean isOnlineMode() {
        return this.plugin.getServer().getOnlineMode();
    }

    @Override
    public void log(String message, Object... values) {
        this.plugin.getLogger().info(Strings.format(message, values));
    }
}
