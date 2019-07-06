package org.laxio.protocol;

import org.laxio.network.connection.Connection;
import org.laxio.network.stream.LaxioInput;
import org.laxio.network.stream.LaxioOutput;
import org.laxio.packet.Packet;

import java.io.IOException;

public interface Protocol {

    int getVersion();

    Packet readPacket(Connection connection, LaxioInput input) throws IOException;

    void writePacket(Connection connection, LaxioOutput output, Packet packet) throws IOException;

}
