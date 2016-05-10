package transfer.test.string;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 日志分析
 * @author Administrator
 *
 */
public class BigLogAnalysize2 {
	
	/** 错误类型统计数量,结果缓存 */
	private Map<String, Integer> cache = new HashMap<String, Integer>();
	
	/** 数据输入流 */
	private DataInputStream dataInputStream;
	/** 文件扫描器 */
	private BufferedInputStream bufferedInputStream;
	/** 文件输入流 */
	private FileInputStream inputStream;

	
	public BigLogAnalysize2(String file) {
		this.initFileInputStream(file);
	}
	
	/**
	 * 初始化文件输入流
	 * @param file 文件
	 */
	private void initFileInputStream(String file) {
		FileInputStream inputStream = null;
		BufferedInputStream bufferedInputStream = null;
		DataInputStream dataInputStream = null;
		try {
		    inputStream = new FileInputStream(file);
		    bufferedInputStream = new BufferedInputStream(inputStream);
		    dataInputStream = new DataInputStream(bufferedInputStream);
		    
			this.inputStream = inputStream;
			this.bufferedInputStream = bufferedInputStream;
			this.dataInputStream = dataInputStream;
		} catch (FileNotFoundException e) {
			throw new RuntimeException("初始化日志分析器异常:", e);
		}
	}
	
	
	/**
	 * 根据错误类型统计数量
	 * @param level 错误级别
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
		
		int count = 0;
		String line = null;
		
		long t1 = System.currentTimeMillis();
		
		int lineNum = 0;
		// 分析文本
		try {
			while((line = dataInputStream.readLine()) != null) {
				if (line.startsWith(level)) {
					count ++;
				}
				if (++lineNum % 1000000 == 0) {
					System.out.println(lineNum);
				}
			}
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
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e1) {
			}
		}
		if (bufferedInputStream != null) {
			try {
				bufferedInputStream.close();
			} catch (IOException e1) {
			}
		}
		if (dataInputStream != null) {
			try {
				dataInputStream.close();
			} catch (IOException e1) {
			}
		}
	}
	
	public static void main(String[] args) {
		int count = new BigLogAnalysize2("1.log").countByLevel("INFO");
		System.out.println("count:" + count);
	}

}
