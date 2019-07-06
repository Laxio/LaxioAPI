package org.laxio.util;

import org.laxio.exception.conditions.IllegalNullException;

public final class Conditions {

    private Conditions() {
        // no public constructor
    }

    /**
     * Checks if the provided object is null
     *
     * @param object the object to check
     *
     * @throws IllegalNullException if the provided object is null
     */
    public static void notNull(Object object) {
        notNull(object, object.getClass().getSimpleName());
    }

    /**
     * Checks if the provided object is null, provides more context in the exception using "name"
     *
     * @param object the object to check
     * @param name the name of the checked object to use in the exception
     *
     * @throws IllegalNullException if the provided object is null
     */
    public static void notNull(Object object, String name) {
        if (object == null) {
            throw new IllegalNullException(name + " cannot be null", object);
        }
    }

}
