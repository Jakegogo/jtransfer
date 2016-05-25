package transfer.test.request;

import java.util.concurrent.atomic.AtomicInteger;

import transfer.anno.Transferable;

/**
 * 请求消息头
 * Created by Jake on 4/30 0030.
 */
@Transferable(id = -1002)
public class RequestHeader {

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
     * 客户机发送请求时间(ms)
     */
    private long requestTime = System.currentTimeMillis();

    /**
     * 接收请求时间(ms)
     */
    private long receiveTime = requestTime;
    
    /**
     * SN码生成器
     */
    private static AtomicInteger snGenerator = new AtomicInteger();
    
    
    public RequestHeader() {
    	this.initSn();
    }
    
    // 初始化SN码
	private void initSn() {
		int sn = snGenerator.incrementAndGet();
		if (sn < 0 || sn >= Integer.MAX_VALUE) {
			snGenerator.set(0);
			sn = snGenerator.incrementAndGet();
		}
		this.sn = sn;
	}

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

	public long getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}

	public long getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}
    
}
