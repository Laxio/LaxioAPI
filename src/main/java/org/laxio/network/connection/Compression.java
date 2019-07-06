package org.laxio.network.connection;

public interface Compression {

    boolean isEnabled();

    int getThreshold();

    void setThreshold(int threshold);

}
