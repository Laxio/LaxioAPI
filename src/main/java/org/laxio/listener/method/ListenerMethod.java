package org.laxio.listener.method;

import org.laxio.exception.listener.InvalidListenerException;
import org.laxio.listener.Listener;
import org.laxio.listener.ListenerManager;
import org.laxio.util.Conditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ListenerMethod {

    private final ListenerManager listenerManager;
    private final Listener listener;
    private final Method method;

    public ListenerMethod(ListenerManager listenerManager, Listener listener, Method method) {
        Conditions.notNull(listenerManager, "listener manager");
        Conditions.notNull(listener, "listener");
        Conditions.notNull(method, "method");

        this.listenerManager = listenerManager;
        this.listener = listener;
        this.method = method;
    }

    /**
     * Returns the listener owner of this method
     *
     * @return The owner of this method
     */
    public Listener getListener() {
        return listener;
    }

    /**
     * Invoke the Listener method with the supplied packet
     *
     * @param object The object to supply
     * @throws InvalidListenerException thrown if there is an issue invoking the method
     */
    public void call(Object object) {
        try {
            this.method.invoke(this.listener, object);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            throw new InvalidListenerException("Unable to invoke method", ex);
        } catch (Exception ex) {
            throw new InvalidListenerException("Unable to run listener method", ex);
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ListenerMethod{");
        sb.append("listenerManager=").append(listenerManager);
        sb.append(", listener=").append(listener);
        sb.append(", method=").append(method);
        sb.append('}');
        return sb.toString();
    }

}
