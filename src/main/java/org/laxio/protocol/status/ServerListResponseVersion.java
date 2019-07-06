package org.laxio.protocol.status;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerListResponseVersion {

    @JsonProperty("name")
    private String name;

    @JsonProperty("protocol")
    private int protocol;

    public ServerListResponseVersion() {
        // required by jackson
    }

    public ServerListResponseVersion(String name, int protocol) {
        this.name = name;
        this.protocol = protocol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

}
