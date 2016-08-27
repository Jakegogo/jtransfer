package transfer.test.request;

import transfer.anno.Transferable;

import java.io.Serializable;

/**
 * 请求消息头
 * Created by Jake on 4/30 0030.
 */
@Transferable(id = -2002)
public class ResponseHeader implements Serializable {

    /**
     * 消息头长度
     */
    private int len = 0;

    /**
     * 是否压缩
     */
    private boolean compressed = false;

    /**
     * SN码
     */
    private int sn = -1;

    /**
     * 模块ID
     */
    private int module;

    /**
     * 命令ID
     */
    private int cmd;

    /**
     * 验证码
     */
    private int authCode;

    /**
     * 接收请求时间(ms)
     */
    private long receiveTime = System.currentTimeMillis();

    /**
     * 响应时间(ms)
     */
    private long responseTime = receiveTime;

    /**
     * 响应标识
     */
    private ResponseStatus status = ResponseStatus.SUCCESS;
    
    
	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public boolean isCompressed() {
		return compressed;
	}

	public void setCompressed(boolean compressed) {
		this.compressed = compressed;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public int getModule() {
		return module;
	}

	public void setModule(int module) {
		this.module = module;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public int getAuthCode() {
		return authCode;
	}

	public void setAuthCode(int authCode) {
		this.authCode = authCode;
	}

	public long getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

}
