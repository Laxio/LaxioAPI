package org.laxio;

import java.util.Set;

public interface Laxio {

    /**
     * Returns a set of applications running on the current JVM
     *
     * @return a set of applications running on the current JVM
     */
    Set<LaxioApplication> getApplications();

    /**
     * Returns the application with the provided name
     *
     * @param name the name to lookup
     * @return the application with the provided name, or null if it does not exist
     */
    LaxioApplication getByName(String name);

}
