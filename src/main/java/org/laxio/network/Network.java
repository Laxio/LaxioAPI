package org.laxio.network;

import org.laxio.Application;

public interface Network {

    Application getApplication();

    void start();

    boolean isRunning();

    void shutdown();

}
