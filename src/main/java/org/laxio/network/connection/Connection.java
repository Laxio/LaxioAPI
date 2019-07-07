package org.laxio.network.connection;

import org.laxio.LaxioApplication;
import org.laxio.packet.Packet;
import org.laxio.protocol.Protocol;
import org.laxio.protocol.ProtocolState;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public interface Connection {

    LaxioApplication getApplication();

    InetSocketAddress getAddress();

    Compression getCompression();

    ProtocolState getProtocolState();

    void setProtocolState(ProtocolState state);

    int getProtocolVersion();

    Protocol getProtocol();

    void setProtocol(Protocol protocol);

    void sendPacket(Packet packet);

}
