package org.laxio.chat;

import org.laxio.command.CommandSender;

public interface ChatController {

    /**
     * Handles the provided message for the given sender.
     * By default, messages prefixed with "/" are considered as commands,
     * however this implementation can change by setting a new chat controller
     *
     * @param sender  The sender of the message
     * @param message The message that was sent
     */
    void handle(CommandSender sender, String message);

}
