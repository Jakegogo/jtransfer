package transfer.test.testing;

import java.lang.reflect.Type;

import com.jake.common.util.JsonUtils;

import transfer.ByteBuffer;
import transfer.Transfer;

/**
 * 测试工具类
 * @author Administrator
 */
public class TestingUtil {
	
	/**
     * Json编码到二进制
     * @param json JSON字符串
     * @param type 指定预编译目标对象的类型
     */
    public static ByteBuffer encodingFromJson(String json, Type type) {
    	Object object = JsonUtils.jsonString2Object(json, type);
        return Transfer.encode(object, type, 128);
    }
    
    
}
