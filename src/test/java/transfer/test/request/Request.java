package transfer.test.request;

import transfer.anno.Transferable;

/**
 * 请求消息格式
 * Created by Jake on 4/30 0030.
 */
@Transferable(id = -1001)
public class Request {

    /**
     * 消息头
     */
    protected RequestHeader header;

    /**
     * 消息体
     */
    protected Object body;
    
    /**
     * 获取实例
     * @param module 模块号
     * @param cmd 协议号
     * @param body 消息体
     * @return
     */
    public static Request valueOf(int module, int cmd, Object body) {
    	Request request = new Request();
    	RequestHeader header = new RequestHeader();
    	header.setModule(module);
    	header.setCmd(cmd);
    	request.header = header;
    	request.body = body;
    	return request;
    }
    
    
    public void setHeader(RequestHeader header) {
		this.header = header;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public RequestHeader getHeader() {
        return header;
    }

    public Object getBody() {
        return body;
    }

}
