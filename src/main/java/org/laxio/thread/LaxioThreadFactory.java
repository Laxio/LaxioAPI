package org.laxio.thread;

import org.laxio.LaxioApplication;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class LaxioThreadFactory implements ThreadFactory {

    private final AtomicInteger id = new AtomicInteger();

    private final LaxioThreadFactory parentFactory;

    private final LaxioApplication application;
    private final String prefix;

    public LaxioThreadFactory(LaxioApplication application) {
        this(application, null);
    }

    public LaxioThreadFactory(LaxioApplication application, String prefix) {
        this.parentFactory = null;
        this.application = application;
        this.prefix = prefix;
    }

    public LaxioThreadFactory(LaxioThreadFactory parentFactory) {
        this(parentFactory, null);
    }

    public LaxioThreadFactory(LaxioThreadFactory parentFactory, String prefix) {
        this.parentFactory = parentFactory;
        this.application = parentFactory.application;
        this.prefix = prefix;
    }

    public String getThreadPrefix() {
        String builtPrefix = getPrefix();
        return application.getName() + (builtPrefix != null ? "-" + builtPrefix : "") + "-";
    }

    public String getPrefix() {
        boolean hasPrefix = prefix != null;

        StringBuilder builder = new StringBuilder();
        if (parentFactory != null) {
            String parentPrefix = parentFactory.getPrefix();
            if (parentPrefix != null) {
                builder.append(parentPrefix);
                if (hasPrefix) {
                    builder.append("-");
                }
            }
        }

        if (hasPrefix) {
            builder.append(prefix);
        }

        return builder.toString();
    }

    @Override
    public Thread newThread(Runnable r) {
        return new LaxioThread(r, getThreadPrefix() + id.getAndIncrement(), application);
    }

}
