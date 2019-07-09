package org.laxio;

import org.laxio.listener.ListenerManager;
import org.laxio.network.Network;
import org.laxio.protocol.ProtocolRegistry;

import java.net.InetSocketAddress;

public interface Application {

    String getName();

    ThreadGroup getThreadGroup();

    Network getNetwork();

    InetSocketAddress getAddress();

    void start();

    boolean isRunning();

    void stop();

    ListenerManager getListenerManager();

    ProtocolRegistry getProtocolRegistry();

}
