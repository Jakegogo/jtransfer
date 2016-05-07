package transfer.exceptions;

/**
 * 解析异常
 * Created by Jake on 5/8 0008.
 */
public class DeserializeException extends RuntimeException {

    public DeserializeException(Throwable throwable) {
        super("解析异常", throwable);
    }

}
