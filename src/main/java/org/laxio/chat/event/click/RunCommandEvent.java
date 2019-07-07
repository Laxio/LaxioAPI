package org.laxio.chat.event.click;


/**
 * Represents the run_command action.
 * Does not need to be a command, but if prefixed with '/' will be run as a command
 */
public class RunCommandEvent implements ClickEvent {

    private final String value;

    public RunCommandEvent(String value) {
        this.value = value;
    }

    @Override
    public String getAction() {
        return "run_command";
    }

    @Override
    public String getValue() {
        return value;
    }

}
