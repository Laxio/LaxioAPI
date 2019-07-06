package org.laxio.network;

import java.net.InetSocketAddress;

public interface NetworkServer extends Network {

    InetSocketAddress getBindAddress();

}
