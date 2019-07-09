package org.laxio.exception.protocol;

public class ProtocolEncryptionException extends RuntimeException {

    public ProtocolEncryptionException(String message) {
        super(message);
    }

    public ProtocolEncryptionException(String message, Throwable cause) {
        super(message, cause);
    }

}
