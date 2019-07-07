package org.laxio.chat.event.hover;

import org.laxio.chat.component.TextComponent;

/**
 * Represents the show_text action.
 * Displays the supplied text component
 */
public class ShowTextEvent implements HoverEvent {

    private final TextComponent text;

    public ShowTextEvent(TextComponent text) {
        this.text = text;
    }

    @Override
    public String getAction() {
        return "show_text";
    }

    @Override
    public Object getValue() {
        if (text.isPureText()) {
            return text.getText();
        }

        return text.toJSON();
    }

}
