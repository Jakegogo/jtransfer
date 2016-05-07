package transfer.exceptions;

/**
 * 序列化异常
 * Created by Jake on 5/8 0008.
 */
public class SerializeException extends RuntimeException {

    public SerializeException(Throwable throwable) {
        super("序列化异常", throwable);
    }

}
