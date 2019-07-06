package org.laxio.exception.thread;

public class ThreadGroupApplicationNotFound extends RuntimeException {

    private final ThreadGroup threadGroup;

    public ThreadGroupApplicationNotFound(ThreadGroup threadGroup) {
        this.threadGroup = threadGroup;
    }

    public ThreadGroup getThreadGroup() {
        return threadGroup;
    }

}
