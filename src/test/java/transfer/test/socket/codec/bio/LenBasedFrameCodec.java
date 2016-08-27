package transfer.test.socket.codec.bio;

import java.io.InputStream;
import java.io.OutputStream;

import transfer.ByteBuffer;
import transfer.Transfer;

/**
 * 请求流编解码器
 * Created by Jake on 5/8 0008.
 */
public class LenBasedFrameCodec implements StreamCodec {

    @Override
    public void encode(Object o, final OutputStream out) {
        ByteBuffer byteBuffer = Transfer.encode(o);
        CodecUtil.writeInt(out, byteBuffer.length());
        byteBuffer.flushToOutputable(CodecUtil.wrapOutputable(out));
    }

    @Override
    public Object decode(final InputStream in) {
    	CodecUtil.readInt(in);
        return Transfer.decode(CodecUtil.wrapInputable(in));
    }

}
