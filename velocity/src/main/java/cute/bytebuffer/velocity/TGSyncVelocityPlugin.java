package cute.bytebuffer.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import cute.bytebuffer.common.Constants;
import cute.bytebuffer.common.MessageCodec;
import net.kyori.adventure.text.Component;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Plugin(id = "tgsync-velocity", name = "TGSyncVelocity", version = "1.0.0")
public class TGSyncVelocityPlugin {

    private final ProxyServer server;
    private final MinecraftChannelIdentifier channel;

    @Inject
    public TGSyncVelocityPlugin(ProxyServer server, Logger logger) {
        this.server = server;
        this.channel = MinecraftChannelIdentifier.from(Constants.channel);
    }

    @Subscribe
    private void onProxyInitalize(ProxyInitializeEvent event){
        server.getChannelRegistrar().register(channel);
    }

    @com.velocitypowered.api.event.Subscribe
    private void onPluginMessage(PluginMessageEvent event){
        if (!event.getIdentifier().equals(channel) ||!(event.getSource() instanceof ServerConnection source)){
            return;
        }

        event.setResult(PluginMessageEvent.ForwardResult.handled());

        Component alert = MessageCodec.decode(event.getData());

        for (Player player : getStaffOnProxy()){
            if (player.getCurrentServer().isPresent()
                    && player.getCurrentServer().get().getServerInfo().equals(source.getServerInfo())) {
                continue;
            }
            player.sendMessage(alert);
        }

    }

    public List<Player> getStaffOnProxy() {
        return server.getAllPlayers().stream()
                .filter(p -> p.hasPermission("totemguard.alerts"))
                .collect(Collectors.toList());

    }

}
