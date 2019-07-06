package org.laxio.exception.thread;

public class ThreadApplicationNotFound extends RuntimeException {

    private final Thread thread;

    public ThreadApplicationNotFound(Thread thread) {
        this.thread = thread;
    }

    public Thread getThread() {
        return thread;
    }

}
