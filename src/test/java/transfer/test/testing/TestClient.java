package transfer.test.testing;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import transfer.test.socket.codec.TransferUtil;

/**
 * Socket 连接客户端
 * @author Administrator
 *
 */
public class TestClient {
	
	private TestClientHandler clientSocketHandler;
	
	public TestClient() {
		TransferUtil.initMeta();
	}
	
	public void connect(String serverIp, int serverPort) throws UnknownHostException, IOException {
		Socket socket = new Socket(InetAddress.getByName(serverIp), serverPort);
		clientSocketHandler = new TestClientHandler();
		clientSocketHandler.handle(socket);
	}
	
	public static void main(String[] args) throws InterruptedException {
		TestClient client = new TestClient();
		try {
			client.connect("127.0.0.1", 8010);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date date = new Date();
		for (int i = 0; i < 164000;i++) {
			client.clientSocketHandler.send(i);
		}
		Thread.sleep(2000);
		System.out.println(new SimpleDateFormat("HH:mm:ss.SSS").format(date));
		System.out.println(new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()));
	}

}
