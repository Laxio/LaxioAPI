package org.laxio.protocol;

import org.laxio.Application;

import java.util.Optional;

public interface ProtocolRegistry {

    Application getApplication();

    void register(Protocol protocol);

    Optional<Protocol> findByVersion(int version);

}
