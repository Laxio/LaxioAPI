package org.laxio.chat.event.click;


/**
 * Represents the suggest_command action.
 * Does not need to be a command, but if prefixed with '/' will be run as a command
 */
public class SuggestCommandEvent implements ClickEvent {

    private final String value;

    public SuggestCommandEvent(String value) {
        this.value = value;
    }

    @Override
    public String getAction() {
        return "suggest_command";
    }

    @Override
    public String getValue() {
        return value;
    }

}
