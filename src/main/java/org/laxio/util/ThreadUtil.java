package org.laxio.util;

import org.laxio.Application;
import org.laxio.exception.thread.ThreadApplicationNotFound;
import org.laxio.exception.thread.ThreadGroupApplicationNotFound;
import org.laxio.thread.LaxioThread;
import org.laxio.thread.LaxioThreadGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ThreadUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadUtil.class);

    private ThreadUtil() {
        // private constructor
    }

    /**
     * Returns the Laxio Application that owns the provided thread
     *
     * @param thread the thread to look for
     * @return the application that owns the provided thread
     *
     * @throws ThreadApplicationNotFound if the thread has no group and is not a LaxioThread
     * @throws ThreadGroupApplicationNotFound if the thread has a group, {@link ThreadUtil#getApplication(ThreadGroup)}
     * @throws org.laxio.exception.conditions.IllegalNullException if the thread has a null application
     */
    public static Application getApplication(Thread thread) {
        if (thread instanceof LaxioThread) {
            LaxioThread lxt = (LaxioThread) thread;
            Application application = lxt.getApplication();
            Conditions.notNull(application, "application");
            return application;
        }

        if (thread.getThreadGroup() == null) {
            throw new ThreadApplicationNotFound(thread);
        }

        return getApplication(thread.getThreadGroup());
    }

    /**
     * Returns the Laxio Application that owns the provided thread group
     *
     * @param threadGroup the thread group to look for
     * @return the application that owns the provided thread group
     *
     * @throws ThreadGroupApplicationNotFound if the thread group has no application in it's inheritance
     * @throws org.laxio.exception.conditions.IllegalNullException if the thread group has a null application
     */
    public static Application getApplication(ThreadGroup threadGroup) {
        if (threadGroup instanceof LaxioThreadGroup) {
            LaxioThreadGroup lxtg = (LaxioThreadGroup) threadGroup;
            Application application = lxtg.getApplication();
            Conditions.notNull(application, "application");
            return application;
        }

        return getApplication(threadGroup, threadGroup);
    }

    private static Application getApplication(ThreadGroup originGroup, ThreadGroup threadGroup) {
        if (threadGroup.getParent() != null) {
            return getApplication(originGroup, threadGroup.getParent());
        } else {
            throw new ThreadGroupApplicationNotFound(originGroup);
        }
    }

}
