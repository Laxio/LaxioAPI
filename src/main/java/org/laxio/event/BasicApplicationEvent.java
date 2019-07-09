package org.laxio.event;

import org.laxio.Application;

public class BasicApplicationEvent implements ApplicationEvent {

    private final Application application;

    public BasicApplicationEvent(Application application) {
        this.application = application;
    }

    @Override
    public Application getApplication() {
        return application;
    }

}
