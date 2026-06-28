package cute.bytebuffer.paper;

import com.google.common.io.ByteArrayDataOutput;
import cute.bytebuffer.common.Constants;
import cute.bytebuffer.paper.listener.FlagListener;
import org.bukkit.plugin.java.JavaPlugin;

public class TGSyncPaperPlugin extends JavaPlugin {

    @Override
    public void onEnable(){
        getServer().getMessenger().registerOutgoingPluginChannel(this, Constants.channel);
        new FlagListener(this);
    }
}
