package org.laxio;

import org.laxio.network.Network;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public interface LaxioApplication {

    String getName();

    ThreadGroup getThreadGroup();

    Network getNetwork();

    InetSocketAddress getAddress();

    void start();

    boolean isRunning();

    void stop();

}
