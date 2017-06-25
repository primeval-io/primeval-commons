package io.primeval.common.widebuffer;

import java.nio.ByteBuffer;

import sun.misc.Cleaner;

@SuppressWarnings("restriction")
public final class WideByteBuffer implements WideBuffer {

    private final ByteBuffer byteBuffer;

    public WideByteBuffer(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    @Override
    public byte get(long pos) {
        return byteBuffer.get((int) pos);
    }

    @Override
    public double getDouble(long pos) {
        return byteBuffer.getDouble((int) pos);
    }

    @Override
    public float getFloat(long pos) {
        return byteBuffer.getFloat((int) pos);
    }

    @Override
    public long getLong(long pos) {
        return byteBuffer.getLong((int) pos);
    }

    @Override
    public int getInt(long pos) {
        return byteBuffer.getInt((int) pos);
    }

    @Override
    public short getShort(long pos) {
        return byteBuffer.getShort((int) pos);
    }

    @Override
    public void destroy() {
        Cleaner cleaner = ((sun.nio.ch.DirectBuffer) byteBuffer).cleaner();
        if (cleaner != null)
            cleaner.clean();
    }

    @Override
    public WideBuffer slice(long startPos, long endPos) {
        ByteBuffer buffer = byteBuffer;
        buffer.position((int) startPos);
        buffer.limit((int) endPos);
        return WideBuffer.wrap(buffer.slice());
    }

    @Override
    public long capacity() {
        return byteBuffer.capacity();
    }

    @Override
    public ByteBuffer asByteBuffer() {
        return byteBuffer;
    }

}
