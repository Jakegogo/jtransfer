package transfer.test.request;

/**
 * 请求消息格式
 * Created by Jake on 4/30 0030.
 */
public class Request {

    /**
     * 消息头
     */
    protected RequestHeader header;

    /**
     * 消息体
     */
    protected Object body;

    public RequestHeader getHeader() {
        return header;
    }

    public Object getBody() {
        return body;
    }


}
