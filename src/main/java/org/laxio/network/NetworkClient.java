package org.laxio.network;

import java.net.InetSocketAddress;

public interface NetworkClient extends Network {

    InetSocketAddress getAddress();

}
