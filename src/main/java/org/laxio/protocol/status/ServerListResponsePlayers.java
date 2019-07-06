package org.laxio.protocol.status;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ServerListResponsePlayers {

    @JsonProperty("max")
    private int max;

    @JsonProperty("online")
    private int online;

    @JsonProperty("sample")
    private List<ServerListResponsePlayersRecord> sample;

    public ServerListResponsePlayers() {
        // required by jackson
    }

    public ServerListResponsePlayers(
            int max, int online, List<ServerListResponsePlayersRecord> sample
    ) {
        this.max = max;
        this.online = online;
        this.sample = sample;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public List<ServerListResponsePlayersRecord> getSample() {
        return sample;
    }

    public void setSample(List<ServerListResponsePlayersRecord> sample) {
        this.sample = sample;
    }

}
