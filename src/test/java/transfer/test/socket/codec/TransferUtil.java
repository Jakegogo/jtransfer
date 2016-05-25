package transfer.test.socket.codec;

import java.util.Collection;

import transfer.anno.Transferable;
import transfer.def.TransferConfig;

import com.jake.common.util.reflect.PackageScanner;

/**
 * 传输工具类
 * @author Administrator
 */
public class TransferUtil {

	/**
	 * 初始化传输数据定义
	 */
	public static void initMeta() {
		Collection<Class<?>> metaClasses = PackageScanner.filterByAnnotation(TransferUtil.class.getClassLoader(), Transferable.class, "transfer.test");
		
		for (Class<?> clz : metaClasses) {
			TransferConfig.preCompileDeserializer(clz);
			TransferConfig.preCompileSerializer(clz);
		}
	}
	
}
