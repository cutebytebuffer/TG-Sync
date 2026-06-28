package cute.bytebuffer.common;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

public class MessageCodec {

    public static byte[] encode(Component alert){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(GsonComponentSerializer.gson().serialize(alert));
        return out.toByteArray();
    }

    public static Component decode(byte[] data){
        ByteArrayDataInput in = ByteStreams.newDataInput(data);
        Component alert = GsonComponentSerializer.gson().deserialize(in.readUTF());
        return alert;
    }

}
