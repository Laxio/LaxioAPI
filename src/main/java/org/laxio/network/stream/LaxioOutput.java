package org.laxio.network.stream;

import java.io.IOException;
import java.util.UUID;

public interface LaxioOutput {

    void write(int value) throws IOException;

    void writeByte(byte value) throws IOException;

    void writeBytes(byte[] value) throws IOException;

    void writeUnsignedByte(int value) throws IOException;

    void writeUnsignedShort(int value) throws IOException;

    void writeFloat(float value) throws IOException;

    void writeDouble(double value) throws IOException;

    void writeShort(short value) throws IOException;

    void writeInt(int value) throws IOException;

    void writeLong(long value) throws IOException;

    void writeBoolean(boolean value) throws IOException;

    void writeUUID(UUID value) throws IOException;

    void writeDashedUUID(UUID value) throws IOException;

    void writeString(String value) throws IOException;

    void writeStringContent(String value) throws IOException;

    void writeVarInt(int value) throws IOException;

    void writeVarLong(long value) throws IOException;

}
