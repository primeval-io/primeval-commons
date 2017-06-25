package io.primeval.common.widebuffer;

import java.nio.ByteBuffer;

public interface WideBuffer {

    public static WideBuffer wrap(ByteBuffer byteBuffer) {
        return new WideByteBuffer(byteBuffer);
    }

    byte get(long pos);

    short getShort(long pos);

    int getInt(long pos);

    long getLong(long pos);

    double getDouble(long pos);
    
    float getFloat(long pos);

    void destroy();

    WideBuffer slice(long startPos, long endPos);

    long capacity();

    // Will throw if it does not fit in a ByteBuffer (2GB)
    ByteBuffer asByteBuffer();


}
