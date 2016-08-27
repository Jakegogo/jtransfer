package transfer.test.testing2;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import transfer.test.request.Request;
import transfer.test.request.Response;
import transfer.test.socket.ClientSocketHandler;

public class TestClientHandler extends ClientSocketHandler {

	protected ObjectInputStream ois;
	protected ObjectOutputStream oos;

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
		this.init(socket);
		
		new Thread() {
			public void run() {
				TestClientHandler.this.handleRead();
			};
		}.start();
	}
	
	public void send(Object data) {
		this.doWriteObject(Request.valueOf(0, 0, data));
	}

	public void init(Socket socket) throws IOException {
		this.socket = socket;
		this.oos = new ObjectOutputStream(socket.getOutputStream());
		this.ois = new ObjectInputStream(socket.getInputStream());
		this.afterInit();
	}


	/**
	 * 读取对象数据
	 *
	 * @return
	 * @throws IOException
	 */
	protected Object doReadObject() throws IOException {
		try {
			return this.ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
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
		try {
			this.oos.writeObject(object);
			this.oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
