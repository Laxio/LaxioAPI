package org.laxio.thread;

import org.laxio.Application;

public class LaxioThreadGroup extends ThreadGroup {

    private final Application application;

    public LaxioThreadGroup(Application application) {
        super(application.getName());
        this.application = application;
    }

    public Application getApplication() {
        return application;
    }

}
