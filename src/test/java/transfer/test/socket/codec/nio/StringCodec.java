package transfer.test.socket.codec.nio;

import java.nio.charset.Charset;

/**
 * 字符串编解码器
 * @author Administrator
 *
 */
public class StringCodec implements Codec {

	Charset charset = Charset.forName("UTF-8");
	
	@Override
	public byte[] encode(Object o) {
		if(o == null) {
			return new byte[0];
		}
		byte[] bytes = String.valueOf(o).getBytes(charset);
		
		return bytes;
	}

	@Override
	public Object decode(byte[] bytes) {
		return new String(bytes, charset);
	}

	
	
}
