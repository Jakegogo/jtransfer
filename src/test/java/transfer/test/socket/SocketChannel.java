package transfer.test.socket;

import transfer.test.socket.codec.bio.JTransferStreamCodec;
import transfer.test.socket.codec.bio.StreamCodec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
 * socket连接通道
 * 
 * @author Administrator
 *
 */
public abstract class SocketChannel {

	/**
	 * 默认编解码器
	 */
	protected static StreamCodec DEFAULT_CODEC = new JTransferStreamCodec();

	/**
	 * 请求关闭指令
	 */
	protected static String CLOSE_COMMAND = "close";
	/**
	 * 字节缓存数量
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	protected Socket socket;

	protected InputStream is;
	protected OutputStream os;

	protected volatile boolean close = false;

	/**
	 * 初始化连接
	 * 
	 * @param socket
	 *            Socket
	 * @throws IOException
	 */
	public void init(Socket socket) throws IOException {
		this.socket = socket;
		this.is = this.socket.getInputStream();
		this.os = this.socket.getOutputStream();
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		this.beforeClose();
		this.close = true;
		try {
			if (this.os != null) {
				this.os.close();
			}
			if (this.is != null) {
				this.is.close();
			}
			if (this.socket != null) {
				this.socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭之前的回调
	 */
	protected void beforeClose() {
		// do nothing
	}

	// --------------- 处理消息 ---------------

	/**
	 * 处理读取
	 */
	public void handleRead() {
		try {

			while (!close && !this.socket.isClosed()) {
				handleInputStream();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

	}
	
	/**
	 * 处理输入流
	 * @throws IOException
	 */
	protected void handleInputStream() throws IOException {
		Object recieveData = this.doReadObject();
		if (CLOSE_COMMAND.equals(recieveData)) {
			this.close = true;
		} else {
			handleMessage(recieveData);
		}
	}

	/**
	 * 处理接收到的消息
	 * 
	 * @param recieveData
	 *            Object
	 * @throws IOException
	 */
	protected abstract void handleMessage(Object recieveData)
			throws IOException;

	// ------------- 协议相关 -------------

	/**
	 * 读取字节数据
	 * 
	 * @return
	 * @throws IOException 
	 */
	protected byte[] doReadBytes() throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int n = 0;
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		while (-1 != (n = is.read(buffer))) {
			output.write(buffer, 0, n);
		}
		byte[] bytes = output.toByteArray();
		output.close();
		return bytes;
	}

	/**
	 * 读取对象数据
	 * 
	 * @return
	 * @throws IOException 
	 */
	protected Object doReadObject() throws IOException {
		return SocketChannel.DEFAULT_CODEC.decode(is);
	}

	/**
	 * 写入数据
	 * 
	 * @param bytes
	 *            输出流
	 * @return
	 */
	protected boolean doWriteBytes(byte[] bytes) {
		try {
			os.write(bytes);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 写入对象数据
	 * 
	 * @return
	 */
	protected boolean doWriteObject(Object object) {
		if (object == null) {
			return false;
		}
		SocketChannel.DEFAULT_CODEC.encode(object, os);
		return true;
	}

}
