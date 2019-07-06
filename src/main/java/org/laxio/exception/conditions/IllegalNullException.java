package org.laxio.exception.conditions;

public class IllegalNullException extends RuntimeException {

    private final Object object;

    public IllegalNullException(String message, Object object) {
        super(message);
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

}
