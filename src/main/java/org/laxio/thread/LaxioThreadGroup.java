package org.laxio.thread;

import org.laxio.LaxioApplication;

public class LaxioThreadGroup extends ThreadGroup {

    private final LaxioApplication application;

    public LaxioThreadGroup(LaxioApplication application) {
        super(application.getName());
        this.application = application;
    }

    public LaxioApplication getApplication() {
        return application;
    }

}
