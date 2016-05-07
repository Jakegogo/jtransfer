package transfer.test.request;

/**
 * 响应消息格式
 * Created by Jake on 4/30 0030.
 */
public class Response {

    /**
     * 消息头
     */
    private ResponseHeader header;

    /**
     * 消息体
     */
    private Object body;

    public ResponseHeader getHeader() {
        return header;
    }

    public Object getBody() {
        return body;
    }
}
