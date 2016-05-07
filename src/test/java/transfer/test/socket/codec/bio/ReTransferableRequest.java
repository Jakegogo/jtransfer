package transfer.test.socket.codec.bio;

import transfer.ByteArray;
import transfer.ByteBuffer;
import transfer.Inputable;
import transfer.Transfer;
import transfer.exceptions.DeserializeException;
import transfer.exceptions.SerializeException;
import transfer.test.request.Request;
import transfer.test.request.RequestHeader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 可转发的Request
 */
class ReTransferableRequest extends Request {
    private final Inputable inputable;
    private final ByteBuffer readedBytes = new ByteBuffer();

    private volatile RequestHeader header;

    private int len;

    public ReTransferableRequest(final InputStream in) {
        Inputable inputable = new Inputable() {
            @Override
            public byte getByte() {
                try {
                    byte byte1 = (byte) in.read();
                    readedBytes.putByte(byte1);
                    return byte1;
                } catch (IOException e) {
                    throw new DeserializeException(e);
                }
            }

            @Override
            public void getBytes(byte[] bytes) {
                try {
                    in.read(bytes);
                    readedBytes.putBytes(bytes);
                } catch (IOException e) {
                    throw new DeserializeException(e);
                }
            }

            @Override
            public ByteArray getByteArray(int length) {
                byte[] bytes = new byte[length];
                try {
                    in.read(bytes);
                    readedBytes.putBytes(bytes);
                    return new ByteArray(bytes);
                } catch (IOException e) {
                    throw new DeserializeException(e);
                }
            }
        };
        this.inputable = inputable;

        byte[] lenBytes = new byte[4];
        try {
            in.read(lenBytes);
        } catch (IOException e) {
            throw new DeserializeException(e);
        }
        this.len = CodecUtil.bytesToInt(lenBytes, 0);
    }

    @Override
    public RequestHeader getHeader() {
        return Transfer.decode(inputable, RequestHeaderDTO.class).getHeader();
    }

    @Override
    public Object getBody() {
        return Transfer.decode(inputable);
    }

    /**
     * 传输到输出流
     * @param out OutputStream
     */
    public void transferTo(OutputStream out) {
        try {
            int flushLen = 0;

            if (header != null) {
                flushLen = readedBytes.flushToOutputable(CodecUtil.wrapOutputable(out));
            }

            byte[] lenBytes = new byte[this.len - flushLen];
            this.inputable.getBytes(lenBytes);
            out.write(lenBytes);
        } catch (IOException e) {
            throw new SerializeException(e);
        }
    }

    public int getLen() {
        return len;
    }
}
