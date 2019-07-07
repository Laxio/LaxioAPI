package org.laxio.exception.chat;

import org.laxio.chat.MessageComponent;

/**
 * Thrown when the format of this supplied component is invalid
 */
public class ChatFormatException extends RuntimeException {

    private static final long serialVersionUID = -8707856963233363958L;

    private final transient MessageComponent component;

    public ChatFormatException(String message, MessageComponent component) {
        super(message);
        this.component = component;
    }

    public MessageComponent getComponent() {
        return component;
    }

}
