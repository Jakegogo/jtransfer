package transfer.test.socket.codec.bio;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 请求流编解码器
 * 用于消息转发和拦截
 * Created by Jake on 5/8 0008.
 */
public class LazyRequestCodec implements StreamCodec {

    private JTransferCodec defaultCodec = new JTransferCodec();

    @Override
    public void encode(Object o, final OutputStream out) {
        // 转发Request
        if (o instanceof LazyRequest) {
            LazyRequest r = ((LazyRequest) o);
            CodecUtil.writeInt(out, r.getLen());
            r.transferTo(out);
            return;
        }
        defaultCodec.encode(o, out);
    }

    @Override
    public Object decode(InputStream in) {
        return new LazyRequest(in);
    }


}
