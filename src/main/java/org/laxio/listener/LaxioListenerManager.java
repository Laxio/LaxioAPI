package org.laxio.listener;

import org.laxio.Application;
import org.laxio.event.Event;
import org.laxio.event.Priority;
import org.laxio.exception.listener.InvalidListenerException;
import org.laxio.listener.method.ListenerMethod;
import org.laxio.listener.method.MethodList;
import org.laxio.packet.Packet;
import org.laxio.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class LaxioListenerManager implements ListenerManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaxioListenerManager.class);

    private final Application application;

    private final MethodList packetList;
    private final MethodList eventList;

    public LaxioListenerManager(Application application) {
        this.application = application;
        this.packetList = new MethodList(this);
        this.eventList = new MethodList(this);
    }

    @Override
    public void register(Plugin plugin, Listener listener) {
        try {
            Method[] methods = listener.getClass().getDeclaredMethods();

            for (Method ref : methods) {
                registerHandler(plugin, listener, ref);
            }
        } catch (InvalidListenerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InvalidListenerException("Error while configuring listener", ex);
        }
    }

    private void registerHandler(Plugin plugin, Listener listener, Method ref) {
        EventHandler eventHandler = ref.getDeclaredAnnotation(EventHandler.class);
        if (eventHandler != null) {
            registerHandler(plugin, listener, ref, EventHandler.class, Event.class, eventHandler.priority(), eventList);
        }

        PacketHandler packetHandler = ref.getDeclaredAnnotation(PacketHandler.class);
        if (packetHandler != null) {
            registerHandler(plugin, listener, ref, PacketHandler.class, Packet.class, packetHandler.priority(), packetList);
        }
    }

    private <T> void registerHandler(Plugin plugin, Listener listener, Method ref, Class<? extends T> annoClass, Class<?> methodType, Priority priority, MethodList list) {
        Class<?>[] args = ref.getParameterTypes();
        if (args.length != 1) {
            throw new InvalidListenerException(annoClass.getSimpleName() + " requires a method with only 1 parameter");
        }

        Class<?> cls = args[0];
        if (methodType.isAssignableFrom(cls)) {
            ListenerMethod method = new ListenerMethod(this, listener, ref);
            list.register(plugin, listener, cls, priority, method);
            return;
        }

        throw new InvalidListenerException(annoClass.getSimpleName() + "s require a single argument which is a subclass of " + methodType.getSimpleName());
    }

    @Override
    public void unregister(Plugin plugin) {
        this.packetList.unregister(plugin);
    }

    @Override
    public void unregister(Listener listener) {
        this.packetList.unregister(listener);
    }

    @Override
    public boolean call(Packet packet) {
        return this.packetList.call(packet);
    }

    @Override
    public boolean call(Event event) {
        return this.eventList.call(event);
    }

}
