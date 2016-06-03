package transfer.test;

import transfer.Transfer;
import transfer.test.request.Request;
import transfer.test.socket.codec.TransferUtil;

/**
 * Created by Administrator on 2015/2/26.
 */
public class TestTransfer2 {

    public static void main(String[] args) {

    	TransferUtil.initMeta();
    	
    	for (int i = 0;i < 164000;i++) {
			Request v = Transfer.decode(Transfer.encode(Request.valueOf(0, 0, i)).getByteArray());
			System.out.println(v.getBody());
		}
    	
    }
    
  


}
