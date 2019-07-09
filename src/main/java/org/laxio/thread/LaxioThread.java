package org.laxio.thread;

import org.laxio.Application;
import org.laxio.util.ThreadUtil;

public class LaxioThread extends Thread {

    private final Application application;

    public LaxioThread(Application application) {
        this.application = application;
    }

    public LaxioThread(Runnable target, Application application) {
        super(target);
        this.application = application;
    }

    public LaxioThread(ThreadGroup group, Runnable target) {
        super(group, target);
        this.application = ThreadUtil.getApplication(group);
    }

    public LaxioThread(String name, Application application) {
        super(name);
        this.application = application;
    }

    public LaxioThread(ThreadGroup group, String name) {
        super(group, name);
        this.application = ThreadUtil.getApplication(group);
    }

    public LaxioThread(Runnable target, String name, Application application) {
        super(target, name);
        this.application = application;
    }

    public LaxioThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        this.application = ThreadUtil.getApplication(group);
    }

    public LaxioThread(
            ThreadGroup group, Runnable target, String name, long stackSize
    ) {
        super(group, target, name, stackSize);
        this.application = ThreadUtil.getApplication(group);
    }

    public Application getApplication() {
        return application;
    }

}
