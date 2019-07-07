package org.laxio.chat.event.click;

import java.net.URL;

/**
 * Represents the open_url action
 */
public class OpenUrlEvent implements ClickEvent {

    private final URL url;

    public OpenUrlEvent(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public String getAction() {
        return "open_url";
    }

    @Override
    public String getValue() {
        return url.toString();
    }

}
