package org.laxio.event;

import org.laxio.Application;

public interface ApplicationEvent extends Event {

    Application getApplication();

}
