package cute.bytebuffer.paper.listener;

import com.deathmotion.totemguard.api.TotemGuardAPI;
import com.deathmotion.totemguard.api.events.FlagEvent;
import com.deathmotion.totemguard.api.interfaces.AbstractCheck;
import com.deathmotion.totemguard.api.interfaces.AlertManager;
import com.deathmotion.totemguard.api.interfaces.TotemUser;
import cute.bytebuffer.common.Constants;
import cute.bytebuffer.common.MessageCodec;
import cute.bytebuffer.paper.TGSyncPaperPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;


public class FlagListener implements Listener {
    private final TGSyncPaperPlugin plugin;

    public FlagListener(TGSyncPaperPlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onFlag(FlagEvent event){
        TotemUser user = event.getTotemUser();
        AbstractCheck check = event.getCheck();

        Component alert = Component.text()
                .append(Component.text("TG [" + Bukkit.getServer().getName() + "] ", NamedTextColor.GOLD, TextDecoration.BOLD))
                .append(Component.text("» ", NamedTextColor.DARK_GRAY))
                .append(Component.text(user.getName(), NamedTextColor.YELLOW))
                .append(Component.text(" failed ", NamedTextColor.GRAY))
                .append(Component.text(check.getCheckName() + " ", NamedTextColor.GOLD))
                .append(Component.text("VL[", NamedTextColor.GRAY))
                .append(Component.text(check.getViolations() + 1 + "/" + check.getMaxViolations(), NamedTextColor.GOLD))
                .append(Component.text("]", NamedTextColor.GRAY))
                .build();

        byte[] payload = MessageCodec.encode(alert);

        Player carrier = Bukkit.getPlayer(user.getUniqueId());

        Bukkit.getServer().sendPluginMessage(plugin, Constants.channel, payload);
    }


}
