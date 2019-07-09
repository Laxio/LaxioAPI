package org.laxio.protocol;

import org.laxio.Application;
import org.laxio.network.connection.Connection;
import org.laxio.network.stream.LaxioInput;
import org.laxio.network.stream.LaxioOutput;
import org.laxio.packet.Packet;
import org.laxio.plugin.Plugin;

import java.io.IOException;

public interface Protocol extends Plugin {

    int getProtocolVersion();

    Packet readPacket(Connection connection, LaxioInput input) throws IOException;

    void writePacket(Connection connection, LaxioOutput output, Packet packet) throws IOException;

    @Override
    default void onLoad() {
        // do nothing
    }

    @Override
    default void onEnable() {
        // do nothing
    }

    @Override
    default void onDisable() {
        // do nothing
    }

    default void onProtocolEnable(Application application) {
        // do nothing
    }

    default void onProtocolDisable(Application application) {
        // do nothing
    }

}
