package org.laxio.event.status;

import org.laxio.Application;
import org.laxio.event.BasicApplicationEvent;
import org.laxio.util.Conditions;

import java.net.InetSocketAddress;

public class ServerListPingEvent extends BasicApplicationEvent {

    private final InetSocketAddress sourceAddress;
    private final int sourceProtocolVersion;
    private final String providedHost;
    private final int providedPort;

    private int protocolVersion;
    private String protocolVersionName;
    private String description;
    private int onlinePlayers;
    private int maxPlayers;

    public ServerListPingEvent(
            Application application,
            InetSocketAddress sourceAddress,
            int sourceProtocolVersion,
            String providedHost,
            int providedPort
    ) {
        super(application);
        this.sourceAddress = sourceAddress;
        this.sourceProtocolVersion = sourceProtocolVersion;
        this.providedHost = providedHost;
        this.providedPort = providedPort;

        this.protocolVersion = sourceProtocolVersion;
        this.protocolVersionName = "Laxio";
        this.description = "Default Message";
        this.onlinePlayers = 0;
        this.maxPlayers = 0;
    }

    public InetSocketAddress getSourceAddress() {
        return sourceAddress;
    }

    public int getSourceProtocolVersion() {
        return sourceProtocolVersion;
    }

    public String getProvidedHost() {
        return providedHost;
    }

    public int getProvidedPort() {
        return providedPort;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getProtocolVersionName() {
        return protocolVersionName;
    }

    public void setProtocolVersionName(String protocolVersionName) {
        Conditions.notNull(protocolVersionName, "protocol version name");
        this.protocolVersionName = protocolVersionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        Conditions.notNull(description, "description");
        this.description = description;
    }

    public int getOnlinePlayers() {
        return onlinePlayers;
    }

    public void setOnlinePlayers(int onlinePlayers) {
        this.onlinePlayers = onlinePlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

}
