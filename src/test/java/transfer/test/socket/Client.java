package transfer.test.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Socket 连接客户端
 * @author Administrator
 *
 */
public class Client {
	
	private ClientSocketHandler clientSocketHandler;
	
	public Client() {
	}
	
	public void connect(String serverIp, int serverPort) throws UnknownHostException, IOException {
		Socket socket = new Socket(InetAddress.getByName(serverIp), serverPort);
		clientSocketHandler = new ClientSocketHandler();
		clientSocketHandler.handle(socket);
	}
	
	public static void main(String[] args) {
		try {
			new Client().connect("127.0.0.1", 8010);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
