package transfer.test.socket.codec.bio;

import transfer.Transfer;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * JTransfer流编解码器
 * Created by Jake on 5/8 0008.
 */
public class JTransferCodec implements StreamCodec {

    @Override
    public void encode(Object o, final OutputStream out) {
        Transfer.encode(CodecUtil.wrapOutputable(out), o);
    }

    @Override
    public Object decode(final InputStream in) {
        return Transfer.decode(CodecUtil.wrapInputable(in));
    }
}
