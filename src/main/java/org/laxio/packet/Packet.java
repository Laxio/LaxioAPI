package org.laxio.packet;

import org.laxio.network.connection.Connection;
import org.laxio.network.stream.LaxioInput;
import org.laxio.network.stream.LaxioOutput;

import java.io.IOException;

public interface Packet {

    Connection getConnection();

    void setConnection(Connection connection);

    void read(LaxioInput input) throws IOException;

    void write(LaxioOutput output) throws IOException;

}
