package org.laxio.network;

import org.laxio.LaxioApplication;

public interface Network {

    LaxioApplication getApplication();

    void start();

    boolean isRunning();

    void shutdown();

}
