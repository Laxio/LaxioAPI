package org.laxio.util;

import org.junit.jupiter.api.Test;
import org.laxio.Application;
import org.laxio.exception.thread.ThreadApplicationNotFound;
import org.laxio.exception.thread.ThreadGroupApplicationNotFound;
import org.laxio.thread.LaxioThread;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ThreadUtilTest {

    @Test
    void getThreadApplication() throws NoSuchFieldException, IllegalAccessException {
        Thread thread = new Thread();

        // since we're not using the thread, we can hack it a little
        setGroup(thread, null);
        assertThrows(ThreadApplicationNotFound.class, () -> ThreadUtil.getApplication(thread), "Standard thread with no group should throw not found");

        Application application = createApplication();
        LaxioThread lxt = new LaxioThread(application);

        assertEquals(application, lxt.getApplication(), "Laxio Thread application should be the one passed in arguments");
        assertEquals(application, ThreadUtil.getApplication(lxt), "Found application should match Thread application");

        Thread thread2 = new Thread();
        assertThrows(ThreadGroupApplicationNotFound.class, () -> ThreadUtil.getApplication(thread2), "Standard thread should throw group not found");
    }

    @Test
    void getThreadGroupApplication() {
        //
    }

    private Application createApplication() {
        return ImplementationProxyUtil.implement(Application.class, new UnsupportedOperationInvocationHandler());
    }

    private void setGroup(Thread thread, ThreadGroup group) throws NoSuchFieldException, IllegalAccessException {
        Field field = Thread.class.getDeclaredField("group");
        field.setAccessible(true);

        field.set(thread, group);

        field.setAccessible(false);
    }

}
