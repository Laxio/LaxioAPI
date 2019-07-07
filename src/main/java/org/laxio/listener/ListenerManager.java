package org.laxio.listener;

import org.laxio.event.Event;
import org.laxio.packet.Packet;
import org.laxio.plugin.Plugin;

public interface ListenerManager {

    /**
     * Register the provided listener
     *
     * @param listener The listener to register
     */
    void register(Plugin plugin, Listener listener);

    /**
     * Unregisters all Listeners for the provided plugin
     *
     * @param plugin The plugin to unregister
     */
    void unregister(Plugin plugin);

    /**
     * Unregister all methods for the provided listener
     *
     * @param listener The listener to unregister
     */
    void unregister(Listener listener);

    /**
     * Calls all handlers listening for the provided Packet
     *
     * @param packet The packet to pass
     */
    boolean call(Packet packet);

    /**
     * Calls all handlers listening for the provided Event
     *
     * @param event The event to pass
     */
    boolean call(Event event);

}
