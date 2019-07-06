package org.laxio.network.stream;

import java.io.IOException;
import java.util.UUID;

public interface LaxioInput {

    short read() throws IOException;

    byte readByte() throws IOException;

    byte[] readBytes(int length) throws IOException;

    default short readUnsignedByte() throws IOException {
        return read();
    }

    int readUnsignedShort() throws IOException;

    float readFloat() throws IOException;

    double readDouble() throws IOException;

    short readShort() throws IOException;

    int readInt() throws IOException;

    long readLong() throws IOException;

    boolean readBoolean() throws IOException;

    UUID readUUID() throws IOException;

    UUID readDashedUUID() throws IOException;

    String readString() throws IOException;

    String readString(int length) throws IOException;

    int readVarInt() throws IOException;

    long readVarLong() throws IOException;

    int readableBytes() throws IOException;

}
