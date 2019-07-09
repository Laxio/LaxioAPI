package org.laxio.event.packet;

import org.laxio.Application;
import org.laxio.network.connection.Connection;
import org.laxio.packet.Packet;

public class PacketInboundEvent extends PacketTransferEvent {

    public PacketInboundEvent(
            Application application, Packet packet, Connection destination
    ) {
        super(application, packet, destination);
    }

}
