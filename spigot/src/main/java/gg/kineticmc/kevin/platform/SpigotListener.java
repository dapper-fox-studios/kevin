package gg.kineticmc.kevin.platform;

import co.schemati.trevor.api.database.DatabaseProxy;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import gg.kineticmc.kevin.KevinSpigot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SpigotListener extends PacketAdapter implements Listener {

    private final KevinSpigot plugin;
    private final DatabaseProxy proxy;

    public SpigotListener(KevinSpigot plugin) {
        super(plugin, PacketType.Status.Server.SERVER_INFO);
        this.plugin = plugin;
        this.proxy = plugin.getCommon().getDatabaseProxy();
    }

    @EventHandler
    public void onPlayerConnect(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        SpigotUser user = new SpigotUser(player.getUniqueId(), player.getName(), event.getHostname());

        proxy.onPlayerConnect(user).thenAccept(result -> {
            if (!result.isAllowed()) {
                result.getMessage().ifPresent(event::setKickMessage);
                event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            }
        });
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        proxy.users().get(event.getPlayer().getUniqueId()).ifPresent(proxy::onPlayerDisconnect);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        PacketContainer packet = event.getPacket();

        packet.getServerPings().read(0).setPlayersOnline(
                plugin.getCommon().getInstanceData().getPlayerCount()
        );
    }

}
