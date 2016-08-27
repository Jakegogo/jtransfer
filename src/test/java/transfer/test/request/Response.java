package transfer.test.request;

import transfer.anno.Transferable;

import java.io.Serializable;

/**
 * 响应消息格式
 * Created by Jake on 4/30 0030.
 */
@Transferable(id = -2001)
public class Response implements Serializable {

    /**
     * 消息头
     */
    private ResponseHeader header;

    /**
     * 消息体
     */
    private Object body;
    
    /**
     * 获取实例
     * @param sn SN码
     * @param module 模块化
     * @param cmd 协议号
     * @param status 状态码
     * @param body 消息体
     * @return
     */
    public static Response valueOf(int sn, int module, int cmd, ResponseStatus status, Object body) {
    	Response response = new Response();
    	ResponseHeader header = new ResponseHeader();
    	header.setSn(sn);
    	header.setModule(module);
    	header.setCmd(cmd);
    	header.setStatus(status);
    	response.header = header;
    	response.body = body;
    	return response;
    }
    
    
    public void setHeader(ResponseHeader header) {
		this.header = header;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public ResponseHeader getHeader() {
        return header;
    }

    public Object getBody() {
        return body;
    }
}
