package org.laxio.listener;

import org.laxio.event.Priority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Defined on methods that are to listen to incoming events
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

    /**
     * Used to define the priority which this method should be fired on
     *
     * @return the priority
     */
    Priority priority() default Priority.NORMAL;

}
