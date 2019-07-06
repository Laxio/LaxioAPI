package org.laxio.protocol.status;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ServerListResponsePlayersRecord {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private UUID uuid;

    public ServerListResponsePlayersRecord() {
        // required by jackson
    }

    public ServerListResponsePlayersRecord(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

}
