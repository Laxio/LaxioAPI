package org.laxio.thread;

import org.laxio.LaxioApplication;
import org.laxio.util.ThreadUtil;

public class LaxioThread extends Thread {

    private final LaxioApplication application;

    public LaxioThread(LaxioApplication application) {
        this.application = application;
    }

    public LaxioThread(Runnable target, LaxioApplication application) {
        super(target);
        this.application = application;
    }

    public LaxioThread(ThreadGroup group, Runnable target) {
        super(group, target);
        this.application = ThreadUtil.getApplication(group);
    }

    public LaxioThread(String name, LaxioApplication application) {
        super(name);
        this.application = application;
    }

    public LaxioThread(ThreadGroup group, String name) {
        super(group, name);
        this.application = ThreadUtil.getApplication(group);
    }

    public LaxioThread(Runnable target, String name, LaxioApplication application) {
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

    public LaxioApplication getApplication() {
        return application;
    }

}
