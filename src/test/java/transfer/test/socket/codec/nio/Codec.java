package transfer.test.socket.codec.nio;

/**
 * 编解码器接口
 */
public interface Codec {

	/**
	 * 编码
	 * @param o 编码目标对象
	 * @return
     */
	byte[] encode(Object o);


	/**
	 * 解码
	 * @param bytes 字节数组
	 * @return
     */
	Object decode(byte[] bytes);
	
}
