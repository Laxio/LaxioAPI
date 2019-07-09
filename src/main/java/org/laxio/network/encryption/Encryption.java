package org.laxio.network.encryption;

import java.security.KeyPair;

public interface Encryption {

    void enable();

    void disable();

    boolean isEnabled();

    KeyPair getKeyPair();

    void setKeyPair(KeyPair keyPair);

    byte[] getSharedSecret();

    void setSharedSecret(byte[] sharedSecret);

    byte[] getVerifyToken();

    void setVerifyToken(byte[] verifyToken);

}
