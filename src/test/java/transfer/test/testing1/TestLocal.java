package transfer.test.testing1;

import transfer.test.request.Response;
import transfer.test.request.ResponseStatus;
import transfer.test.socket.SocketChannel;
import transfer.test.socket.codec.bio.LenBasedFrameCodec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jake on 8/27 0027.
 */
public class TestLocal {

    public static void main(String[] args) {
        LenBasedFrameCodec codec = new LenBasedFrameCodec();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        for (int i = 0; i < 164000;i++) {
            codec.encode(Response.valueOf(999, 0, 0,
                    ResponseStatus.SUCCESS, new StringBuilder("your send message is : ")
                            .append(i)
                            .append(" server time: ")
                            .append(new SimpleDateFormat("HH:mm:ss").format(new Date()))
                            .toString()), os);
            System.out.println("send " + i);


        }


        byte[] bytes = os.toByteArray();
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);


        try {
            Response j;
            do {
                j = (Response) codec.decode(is);
                System.out.println(j.getBody());
            } while (j != null);
        } catch (Exception e) {}
        System.out.println(bytes.length);
    }

}
