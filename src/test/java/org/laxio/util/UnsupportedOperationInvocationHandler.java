package org.laxio.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UnsupportedOperationInvocationHandler implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnsupportedOperationInvocationHandler.class);

    private static final Method EQUALS_METHOD;
    private static final Method HASH_CODE_METHOD;
    private static final Method TO_STRING_METHOD;

    static {
        Method equals;

        try {
            equals = Object.class.getMethod("equals", Object.class);
        } catch (NoSuchMethodException ex) {
            LOGGER.error("Unable to find equals method", ex);
            equals = null;
        }

        EQUALS_METHOD = equals;

        Method hashCode;

        try {
            hashCode = Object.class.getMethod("hashCode");
        } catch (NoSuchMethodException ex) {
            LOGGER.error("Unable to find hashCode method", ex);
            hashCode = null;
        }

        HASH_CODE_METHOD = hashCode;

        Method toString;

        try {
            toString = Object.class.getMethod("toString");
        } catch (NoSuchMethodException ex) {
            LOGGER.error("Unable to find toString method", ex);
            toString = null;
        }

        TO_STRING_METHOD = toString;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (method.equals(EQUALS_METHOD)) {
            Object param = args[0];
            if (param == null || !Proxy.isProxyClass(param.getClass())) {
                return false;
            }
            InvocationHandler other = Proxy.getInvocationHandler(param);
            return equals(other);
        } else if (method.equals(HASH_CODE_METHOD)) {
            return hashCode();
        } else if (method.equals(TO_STRING_METHOD)) {
            return proxy.getClass().getName() + "@" + Integer.toHexString(proxy.hashCode());
        }

        throw new UnsupportedOperationException();
    }

}
