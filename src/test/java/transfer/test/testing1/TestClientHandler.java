package transfer.test.testing1;

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import transfer.test.request.Request;
import transfer.test.request.Response;
import transfer.test.socket.ClientSocketHandler;

public class TestClientHandler extends ClientSocketHandler {

	@Override
	protected void handleMessage(Object recieveData) throws IOException {
		Response response = (Response) recieveData;
		System.out.println(new StringBuilder("get response : ")
				.append(response.getBody())
				.append(" client time: ")
				.append(new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()))
				.toString());
	}

	@Override
	public void handle(Socket socket) throws IOException {
		super.init(socket);
		
		new Thread() {
			public void run() {
				TestClientHandler.this.handleRead();
			};
		}.start();
	}
	
	public void send(Object data) {
		this.doWriteObject(Request.valueOf(0, 0, data));
	}
	
}
