package org.laxio.server;

public interface ServerConfiguration {

    boolean isAuthenticated();

    void setAuthenticated(boolean authenticated);

    boolean isEncrypted();

    void setEncrypted(boolean encrypted);

}
