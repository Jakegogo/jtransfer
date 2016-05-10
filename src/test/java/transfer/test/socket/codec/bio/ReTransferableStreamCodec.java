package transfer.test.socket.codec.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import transfer.exceptions.SerializeException;

/**
 * 请求流编解码器
 * 用于消息转发和拦截
 * Created by Jake on 5/8 0008.
 */
public class ReTransferableStreamCodec implements StreamCodec {

    private JTransferStreamCodec defaultCodec = new JTransferStreamCodec();

    @Override
    public void encode(Object o, final OutputStream out) {
        // 转发Request
        if (o instanceof ReTransferableRequest) {
            ReTransferableRequest r = ((ReTransferableRequest) o);
            try {
                out.write(r.getLen());
            } catch (IOException e) {
                throw new SerializeException(e);
            }

            r.transferTo(out);
            return;
        }
        defaultCodec.encode(o, out);
    }

    @Override
    public Object decode(InputStream in) {
        return new ReTransferableRequest(in);
    }


}
