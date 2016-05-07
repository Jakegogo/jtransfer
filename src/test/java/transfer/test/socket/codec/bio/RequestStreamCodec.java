package transfer.test.socket.codec.bio;

import transfer.ByteBuffer;
import transfer.Transfer;
import transfer.exceptions.SerializeException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 请求流编解码器
 * Created by Jake on 5/8 0008.
 */
public class RequestStreamCodec implements StreamCodec {

    @Override
    public void encode(Object o, final OutputStream out) {
        ByteBuffer byteBuffer = Transfer.encode(o);
        try {
            out.write(byteBuffer.length());
        } catch (IOException e) {
            throw new SerializeException(e);
        }
        byteBuffer.flushToOutputable(CodecUtil.wrapOutputable(out));
    }

    @Override
    public Object decode(final InputStream in) {
        try {
            in.read(new byte[4]);
        } catch (IOException e) {
            throw new SerializeException(e);
        }
        return Transfer.decode(CodecUtil.wrapInputable(in));
    }

}
