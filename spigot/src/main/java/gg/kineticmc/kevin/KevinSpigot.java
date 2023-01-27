package gg.kineticmc.kevin;

import co.schemati.trevor.common.TrevorCommon;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import gg.kineticmc.kevin.platform.SpigotListener;
import gg.kineticmc.kevin.platform.SpigotPlatform;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class KevinSpigot extends JavaPlugin {

    private SpigotPlatform platform;
    private TrevorCommon common;
    private ProtocolManager protocolManager;

    @Override
    public void onLoad() {
        this.platform = new SpigotPlatform(this);
        this.common = new TrevorCommon(this.platform);
        this.protocolManager = ProtocolLibrary.getProtocolManager();

        this.platform.init();

        common.load();
    }

    @Override
    public void onEnable() {
        Listener listener = new SpigotListener(this);
        this.getServer().getPluginManager().registerEvents(listener, this);
        this.protocolManager.addPacketListener((PacketAdapter) listener);

        common.start();
    }

    @Override
    public void onDisable() {
        common.stop();
    }

    public SpigotPlatform getPlatform() {
        return this.platform;
    }

    public TrevorCommon getCommon() {
        return this.common;
    }

}