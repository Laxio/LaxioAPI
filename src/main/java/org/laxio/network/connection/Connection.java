package org.laxio.network.connection;

import org.laxio.Application;
import org.laxio.authentication.ConnectionProfile;
import org.laxio.chat.MessageComponent;
import org.laxio.network.encryption.Encryption;
import org.laxio.packet.Packet;
import org.laxio.protocol.Protocol;
import org.laxio.protocol.ProtocolState;

import java.net.InetSocketAddress;
import java.security.KeyPair;

public interface Connection {

    Application getApplication();

    InetSocketAddress getAddress();

    Compression getCompression();

    Encryption getEncryption();

    ConnectionProfile getProfile();

    void setProfile(ConnectionProfile profile);

    ProtocolState getProtocolState();

    void setProtocolState(ProtocolState state);

    int getProtocolVersion();

    Protocol getProtocol();

    void setProtocol(Protocol protocol);

    void sendPacket(Packet packet);

    void disconnect(Throwable throwable);

    void disconnect(MessageComponent reason);

}
