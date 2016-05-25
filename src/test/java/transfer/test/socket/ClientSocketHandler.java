package transfer.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import transfer.test.request.Request;
import transfer.test.request.Response;
import transfer.test.socket.codec.TransferUtil;

/**
 * Client Socket处理器
 * 
 * @author Administrator
 *
 */
public class ClientSocketHandler extends SocketChannel {

	public void handle(Socket socket) throws IOException {
		super.init(socket);
		
		TransferUtil.initMeta();
		
		new Thread() {
			public void run() {
				ClientSocketHandler.this.handleRead();
			};
		}.start();
		new Thread() {
			public void run() {
				handleUserInput();
			};
		}.start();
	}

	/**
	 * 处理用户输入,并将输入数据发送请求到服务端
	 */
	protected void handleUserInput() {
		// 键盘输入流

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {

			System.out.println("请输入：");
			// 接收输入
			try {
				String s = br.readLine();
				this.doWriteObject(Request.valueOf(0, 0, s));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 处理请求
	 * 
	 * @throws IOException
	 */
	protected void handleMessage(Object recieveData) throws IOException {
		if (CLOSE_COMMAND.equals(recieveData)) {
			this.close = true;

			System.out.println("server notice close.");

		} else {
			String response = resolveResponse(recieveData);
			System.out.println(response);
		}
	}

	/**
	 * 处理请求
	 * 
	 * @param data
	 * @return
	 */
	private String resolveResponse(Object data) {
		Response response = (Response) data;
		try {
			return new StringBuilder("get response : ")
					.append(response.getBody())
					.append(" client time: ")
					.append(new SimpleDateFormat("HH:mm:ss").format(new Date()))
					.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "exception occurred";
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		super.close();
		System.out.println("client " + this.socket.getRemoteSocketAddress()
				+ " closed.");
	}

}
