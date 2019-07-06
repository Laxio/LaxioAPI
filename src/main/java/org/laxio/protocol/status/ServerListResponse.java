package org.laxio.protocol.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerListResponse {

    @JsonProperty("version")
    private ServerListResponseVersion version;

    @JsonProperty("players")
    private ServerListResponsePlayers players;

    @JsonProperty("description")
    private ServerListResponseDescription description;

    @JsonProperty("favicon")
    @JsonInclude(Include.NON_NULL)
    private String favicon;

    public ServerListResponse() {
        // required by jackson
    }

    public ServerListResponse(
            ServerListResponseVersion version,
            ServerListResponsePlayers players,
            ServerListResponseDescription description,
            String favicon
    ) {
        this.version = version;
        this.players = players;
        this.description = description;
        this.favicon = favicon;
    }

    public ServerListResponseVersion getVersion() {
        return version;
    }

    public void setVersion(ServerListResponseVersion version) {
        this.version = version;
    }

    public ServerListResponsePlayers getPlayers() {
        return players;
    }

    public void setPlayers(ServerListResponsePlayers players) {
        this.players = players;
    }

    public ServerListResponseDescription getDescription() {
        return description;
    }

    public void setDescription(ServerListResponseDescription description) {
        this.description = description;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

}
