package io.primeval.common.bytebuffer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public final class ByteBufferListInputStream extends InputStream {
    private final List<ByteBuffer> fromBuffers;
    private int currBuffer = 0;

    public ByteBufferListInputStream(List<ByteBuffer> fromBuffers) {
        this.fromBuffers = fromBuffers;
    }

    @Override
    public int read() throws IOException {
        ByteBuffer byteBuffer = currentByteBuffer();
        if (byteBuffer == null) {
            return -1;
        }
        return byteBuffer.get() & 0xFF;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (len <= 0) {
            return 0;
        }

        ByteBuffer byteBuffer = currentByteBuffer();
        if (byteBuffer == null) {
            return -1;
        }

        int written = 0;

        while (len > written) {

            if (byteBuffer == null) {
                return written;
            }

            // Remaining in current
            int currBuffRemaining = byteBuffer.remaining();

            int toWrite = Math.min(len - written, currBuffRemaining);

            byteBuffer.get(b, off + written, toWrite);
            written += toWrite;
            byteBuffer = currentByteBuffer();

        }
        return written;
    }

    private ByteBuffer currentByteBuffer() {
        ByteBuffer byteBuffer;
        do {
            if (currBuffer >= fromBuffers.size()) {
                return null;
            }
            byteBuffer = fromBuffers.get(currBuffer);
            if (!byteBuffer.hasRemaining()) {
                currBuffer++;
                continue;
            }
            break;

        } while (true);
        return byteBuffer;
    }
}