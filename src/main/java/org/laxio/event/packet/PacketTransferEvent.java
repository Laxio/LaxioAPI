package org.laxio.event.packet;

import org.laxio.Application;
import org.laxio.event.BasicApplicationEvent;
import org.laxio.network.connection.Connection;
import org.laxio.packet.Packet;

public class PacketTransferEvent extends BasicApplicationEvent {

    private final Packet packet;
    private final Connection destination;
    private boolean dropped;

    public PacketTransferEvent(
            Application application, Packet packet, Connection destination
    ) {
        super(application);
        this.packet = packet;
        this.destination = destination;
        this.dropped = false;
    }

    public Packet getPacket() {
        return packet;
    }

    public Connection getDestination() {
        return destination;
    }

    public boolean isDropped() {
        return dropped;
    }

    public void setDropped(boolean dropped) {
        this.dropped = dropped;
    }

}
