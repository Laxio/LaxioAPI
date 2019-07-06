package org.laxio.exception.protocol;

public class InternalProtocolException extends RuntimeException {

    public InternalProtocolException(String message) {
        super(message);
    }

    public InternalProtocolException(String message, Throwable cause) {
        super(message, cause);
    }

}
