package transfer.test.socket.codec.bio;

import transfer.test.request.RequestHeader;

/**
 * 消息头解析DTO
 */
public class LazyRequestHeader {
    /**
     * 消息头
     */
    private RequestHeader header;

    public RequestHeader getHeader() {
        return header;
    }

}
