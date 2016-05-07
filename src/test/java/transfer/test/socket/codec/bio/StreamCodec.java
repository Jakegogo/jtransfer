package transfer.test.socket.codec.bio;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 基于流的编解码器
 * Created by Jake on 5/8 0008.
 */
public interface StreamCodec {

    /**
     * 编码
     * @param o 编码目标对象
     * @param out 输出流
     */
    void encode(Object o, OutputStream out);


    /**
     * 解码
     * @param in 输入流
     * @return
     */
    Object decode(InputStream in);

}

