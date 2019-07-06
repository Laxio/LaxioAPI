package org.laxio.protocol.status;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerListResponseDescription {

    @JsonProperty("text")
    private String text;

    public ServerListResponseDescription() {
        // required by jackson
    }

    public ServerListResponseDescription(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
