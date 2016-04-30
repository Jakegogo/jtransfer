package transfer.test.request;

/**
 * 请求消息头
 * Created by Jake on 4/30 0030.
 */
public class ResponseHeader {

    /**
     * 消息总长度
     */
    private int len = 0;

    /**
     * 是否压缩
     */
    private boolean isCompressed = false;

    /**
     * 流水号
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

}
