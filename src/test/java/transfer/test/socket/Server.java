package transfer.test.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Socket服务端
 * 
 * @author Administrator
 *
 */
public class Server {

	private static int SOCKET_SO_TIME_OUT = 10 * 1000;// 毫秒

	private int port = 8010;

	private boolean stop = false;

	public Server() {
	}

	/**
	 * 初始化ServerSocket
	 * 
	 * @throws IOException
	 */
	private void startListen() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(port));
			serverSocket.setSoTimeout(SOCKET_SO_TIME_OUT);
			
			System.out.println("server started on port " + port);
			
			listen(serverSocket);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
				}
			}
		}

	}

	/**
	 * 监听连接
	 * @param serverSocket
	 */
	private void listen(ServerSocket serverSocket) {
		System.out.println("start listen.");
		Socket accepted = null;
		while (!stop) {
			try {
				accepted = serverSocket.accept();
				new ServerSocketHandler().handle(accepted);
			} catch (SocketTimeoutException e) {
				// ignore
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭服务端
	 */
	public void stop() {
		this.stop = true;
		System.out.println("server closed.");
	}

	public static void main(String[] args) {
		new Server().startListen();
	}

}
