package transfer.test.string;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StreamTokenizer;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 日志分析
 * 
 * @author Administrator
 *
 */
public class BigLogAnalysize3 {

	static int BUFFER_SIZE = 0x8FFFFFF;// 128M

	/** 错误类型统计数量,结果缓存 */
	private Map<String, Integer> cache = new HashMap<String, Integer>();

	RandomAccessFile randomAccessFile = null;
	FileChannel fileChannel = null;
	MappedByteBuffer mappedByteBuffer = null;
	long fileLength = 0;

	public BigLogAnalysize3(String file) {
		this.initFileInputStream(file);
	}

	/**
	 * 初始化文件输入流
	 * 
	 * @param file
	 *            文件
	 */
	private void initFileInputStream(String file) {
		RandomAccessFile randomAccessFile = null;
		FileChannel fileChannel = null;
		MappedByteBuffer mappedByteBuffer = null;
		long fileLength = 0;
		try {
			randomAccessFile = new RandomAccessFile(file, "r");
			fileChannel = randomAccessFile.getChannel();

			fileLength = randomAccessFile.length();

			this.randomAccessFile = randomAccessFile;
			this.fileChannel = fileChannel;
			this.fileLength = fileLength;

		} catch (FileNotFoundException e) {
			throw new RuntimeException("初始化日志分析器异常:", e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据错误类型统计数量
	 * 
	 * @param level
	 *            错误级别
	 * @return
	 */
	public int countByLevel(String level) {
		if (level == null || "".equals(level)) {
			return 0;
		}
		// 检查缓存
		if (cache.containsKey(level)) {
			return cache.get(level);
		}

		long t1 = System.currentTimeMillis();

		int count = 0;
		int lineNum = 0;
		// 分析文本
		try {
			mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY,
					0, fileLength);

			byte[] dst = new byte[BUFFER_SIZE];// 每次读出BUFFER_SIZE的内容

			for (int offset = 0; offset < fileLength; offset += BUFFER_SIZE) {

				if (fileLength - offset >= BUFFER_SIZE) {
					mappedByteBuffer.get(dst);
				} else {
					dst = new byte[(int) (fileLength - offset)];
					mappedByteBuffer.get(dst);
				}
				
				// 将得到的3M内容给Scanner，这里的XXX是指Scanner解析的分隔符
				DataInputStream dataInputStream = new DataInputStream(
						new ByteArrayInputStream(dst));
				String line = null;
				while ((line = dataInputStream.readLine()) != null) {
					if (line.startsWith(level)) {
						count++;
					}
					if (++lineNum % 1000000 == 0) {
						System.out.println(lineNum);
					}
				}
			}

			close();
			// 存入缓存
			cache.put(level, count);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("total line: " + lineNum);
		System.out.println("use time: " + (System.currentTimeMillis() - t1));

		return count;
	}

	/**
	 * 关闭日志分析器
	 */
	public void close() {

		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				try {
					Method getCleanerMethod = mappedByteBuffer.getClass()
							.getMethod("cleaner", new Class[0]);
					getCleanerMethod.setAccessible(true);
					sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod
							.invoke(mappedByteBuffer, new Object[0]);
					cleaner.clean();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	public static void main(String[] args) {
		int count = new BigLogAnalysize3("1.log").countByLevel("INFO");
		System.out.println("count:" + count);
	}

}
