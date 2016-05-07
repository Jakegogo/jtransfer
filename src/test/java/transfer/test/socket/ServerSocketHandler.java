package transfer.test.socket;

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ServerSocket处理器
 * 
 * @author Administrator
 *
 */
public class ServerSocketHandler extends SocketChannel {

	/**
	 * 处理接收到的连接
	 * @param socket Socket
	 * @throws IOException
	 */
	public void handle(Socket socket) throws IOException {
		super.init(socket);
		new Thread() {
			public void run() {
				ServerSocketHandler.this.handleRead();
			};
		}.start();
	}


	/**
	 * 处理请求
	 * 
	 * @throws IOException
	 */
	protected void handleMessage(Object recieveData) throws IOException {
		if (CLOSE_COMMAND.equals(recieveData)) {
			this.close = true;

			System.out.println("client " + this.socket.getRemoteSocketAddress()
					+ " request disconnet.");

		} else {
			String response = dispatchRequest(recieveData);
			this.doWriteObject(response);
		}
	}

	/**
	 * 处理请求
	 * 
	 * @param data
	 * @return
	 */
	private String dispatchRequest(Object data) {
		try {
			return new StringBuilder("your send message is : ")
					.append(data)
					.append(" server time: ")
					.append(new SimpleDateFormat("HH:mm:ss").format(new Date()))
					.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exception occurred";
	}
	
	/**
	 * 关闭之前的回调
	 */
	protected void beforeClose() {
		System.out.println("client "
				+ this.socket.getRemoteSocketAddress()
				+ " request disconnet.");
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		super.close();
		System.out.println("client " + this.socket.getRemoteSocketAddress()
				+ " closed by server listened on " + this.socket.getLocalPort());
	}

}
