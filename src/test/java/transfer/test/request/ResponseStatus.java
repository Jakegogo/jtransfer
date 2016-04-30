package transfer.test.request;

/**
 * 响应消息状态
 * 
 * @author bingshan
 */
public enum ResponseStatus {
	
	/**
	 * 未知错误
	 */
	ERROR(-1),
	
	/**
	 * 操作成功
	 */
	SUCCESS(0),
	
	/**
	 * 协议解析错误
	 */
	RESOLVE_ERROR(2),
	
	/**
	 * 没有权限
	 */
	NO_RIGHT(3),
	
	/**
	 * 验证码错
	 */
	AUTH_CODE_ERROR(4),
	
	/**
	 * session已过期
	 */
	SESSION_EXPIRED(5),
	
	/**
	 * 请求流水非顺序
	 */
	REQUEST_SN_NOT_ORDER(6);
	
	/**
	 * 状态码
	 */
	final int value;
	
	private ResponseStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	/**
	 * 根据状态值取得 ResponseStatus
	 * @param value 状态值
	 * @return ResponseStatus
	 */
	public static ResponseStatus valueOf(int value) {
		for (ResponseStatus status: ResponseStatus.values()) {
			if (status.getValue() == value) {
				return status;
			}
		}
		return null;
	}
}
