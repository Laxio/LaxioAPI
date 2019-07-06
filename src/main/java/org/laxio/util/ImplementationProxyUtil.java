package org.laxio.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public final class ImplementationProxyUtil {

    private ImplementationProxyUtil() {
        // private constructor
    }

    public static <T> T implement(Class<T> ifx, InvocationHandler handler) {
        return implement(ifx.getClassLoader(), ifx, handler);
    }

    @SuppressWarnings("unchecked")
    public static <T> T implement(ClassLoader classLoader, Class<T> ifx, InvocationHandler handler) {
        Object instance = Proxy.newProxyInstance(classLoader, new Class[]{ifx}, handler);
        return (T) instance;
    }

}
