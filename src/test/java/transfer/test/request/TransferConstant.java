package transfer.test.request;

import java.nio.charset.Charset;


/**
 * 传输常量
 * @author jake
 */
public interface TransferConstant {

	/**
	 * 包头最小长度
	 */
	final int PACKAGE_HEADER_MIN_LENGTH = 4;

	/**
	 * Charset
	 */
	final Charset CHARSET = Charset.forName("UTF-8");
	
	/**
	 * flash策略请求信息
	 */
	final byte[] FLASH_POLICY_REQUEST = "<policy-file-request/>".getBytes(CHARSET);
	
	/**
	 * flash策略响应信息
	 */
	final byte[] FLASH_POLICY_RESPONSE = "<?xml version=\"1.0\"?><cross-domain-policy><site-control permitted-cross-domain-policies=\"all\"/><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>\0".getBytes(CHARSET);

}
