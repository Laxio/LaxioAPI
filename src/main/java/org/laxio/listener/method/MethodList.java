package org.laxio.listener.method;

import org.laxio.event.Priority;
import org.laxio.exception.listener.InvalidListenerException;
import org.laxio.listener.Listener;
import org.laxio.listener.ListenerManager;
import org.laxio.packet.Packet;
import org.laxio.plugin.Plugin;
import org.laxio.util.Conditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MethodList {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodList.class);

    private final Lock lock = new ReentrantLock();

    private final ListenerManager listenerManager;

    private final Map<Plugin, Set<Listener>> listeners;
    private final Map<Plugin, Set<ListenerMethod>> pluginListeners;

    private final Map<Class<?>, Map<Priority, Set<ListenerMethod>>> cache;
    private final Map<Class<?>, Map<Priority, Set<ListenerMethod>>> methods;

    public MethodList(ListenerManager listenerManager) {
        this.listenerManager = listenerManager;

        this.listeners = new ConcurrentHashMap<>();
        this.pluginListeners = new ConcurrentHashMap<>();

        this.cache = new ConcurrentHashMap<>();
        this.methods = new ConcurrentHashMap<>();
    }

    /**
     * Returns a set of registered listeners
     *
     * @return A set of registered listeners
     */
    public Set<Listener> getListeners() {
        Set<Listener> all = new HashSet<>();
        for (Set<Listener> set : this.listeners.values()) {
            all.addAll(set);
        }

        return all;
    }

    /**
     * Call the listener methods for the supplied Packet
     *
     * @param object The Object to lookup and pass through
     */
    public boolean call(Object object) {
        Conditions.notNull(object, "object");

        if (lock.tryLock()) {
            try {
                // Pulls cache for that Packet type
                Map<Priority, Set<ListenerMethod>> map = getPriorityMap(object);

                // Call the methods, in order of priority
                return callMethods(map, object);
            } catch (Throwable ex) {
                LOGGER.error("Uncaught exception thrown in listener call", ex);
                return false;
            } finally {
                lock.unlock();
            }
        }

        return true;
    }

    private synchronized Map<Priority, Set<ListenerMethod>> getPriorityMap(Object object) {
        Map<Priority, Set<ListenerMethod>> map = this.cache.get(object.getClass());
        if (map == null) {
            map = new ConcurrentHashMap<>();

            // No cache present, create a new entry and populate
            for (Map.Entry<Class<?>, Map<Priority, Set<ListenerMethod>>> entry : this.methods.entrySet()) {
                if (entry.getKey().isAssignableFrom(object.getClass())) {
                    Map<Priority, Set<ListenerMethod>> value = entry.getValue();
                    for (Map.Entry<Priority, Set<ListenerMethod>> entry2 : value.entrySet()) {
                        // If the entry2 key is no present, populate with a new HashSet and populate that set with the data
                        map.computeIfAbsent(entry2.getKey(), k -> new HashSet<>()).addAll(entry2.getValue());
                    }
                }
            }

            this.cache.put(object.getClass(), map);
        }

        return map;
    }

    private boolean callMethods(Map<Priority, Set<ListenerMethod>> map, Object object) {
        for (Priority priority : Priority.values()) {
            Set<ListenerMethod> set = map.get(priority);

            if (set != null) {
                for (ListenerMethod method : set) {
                    try {
                        method.call(object);
                    } catch (InvalidListenerException ex) {
                        LOGGER.error("Issue while calling listener", ex);
                    }
                }
            }
        }

        return true;
    }

    /**
     * Register a listener
     *
     * @param plugin   The owner of the listener
     * @param listener The listener
     * @param cls      The packet type of the method
     * @param priority The priority of the handler
     * @param method   The method reference to store
     */
    public void register(Plugin plugin, Listener listener, Class<?> cls, Priority priority, ListenerMethod method) {
        synchronized (this.lock) {
            Map<Priority, Set<ListenerMethod>> map = this.methods.computeIfAbsent(cls, k -> new EnumMap<>(Priority.class));
            map.computeIfAbsent(priority, k -> new HashSet<>()).add(method);

            // Populate plugin set if it doesn't exist, add our newly registered method
            this.listeners.computeIfAbsent(plugin, k -> new HashSet<>()).add(listener);
            this.pluginListeners.computeIfAbsent(plugin, k -> new HashSet<>()).add(method);
        }
    }

    /**
     * Unregisters all Listeners for the provided plugin
     *
     * @param plugin The plugin to unregister
     */
    public void unregister(Plugin plugin) {
        synchronized (this.lock) {
            this.listeners.remove(plugin);

            Set<ListenerMethod> registered = this.pluginListeners.remove(plugin);
            if (registered == null) {
                return;
            }

            remove(this.cache, registered);
            remove(this.methods, registered);
        }
    }

    /**
     * Unregister all methods for the provided listener
     *
     * @param listener The listener to unregister
     */
    public void unregister(Listener listener) {
        synchronized (this.lock) {
            Set<ListenerMethod> registered = new HashSet<>();
            for (Set<ListenerMethod> set : this.pluginListeners.values()) {
                Set<ListenerMethod> remove = new HashSet<>();

                for (ListenerMethod method : set) {
                    if (method.getListener().equals(listener)) {
                        registered.add(method);
                        remove.add(method);
                    }
                }

                set.removeAll(remove);
            }

            for (Set<Listener> set : this.listeners.values()) {
                set.remove(listener);
            }

            remove(this.cache, registered);
            remove(this.methods, registered);
        }
    }

    /**
     * Remove the set of registered methods from the supplied store
     *
     * @param store      The store of methods to remove from
     * @param registered The set of registered methods
     */
    private void remove(Map<Class<?>, Map<Priority, Set<ListenerMethod>>> store, Set<ListenerMethod> registered) {
        for (Map<Priority, Set<ListenerMethod>> priorityMap : store.values()) {
            for (Set<ListenerMethod> listenerStore : priorityMap.values()) {
                // Remove all of the provided set of registered listeners from the set of registered ones
                listenerStore.removeAll(registered);
            }
        }
    }

}
