package org.laxio.exception.authentication;

public class SessionAuthenticationException extends RuntimeException {

    public SessionAuthenticationException(String message) {
        super(message);
    }

    public SessionAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

}
