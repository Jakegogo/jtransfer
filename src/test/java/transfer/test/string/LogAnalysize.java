package transfer.test.string;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 日志分析
 * @author Administrator
 *
 */
public class LogAnalysize {
	
	/** 错误类型统计数量,结果缓存 */
	private Map<String, Integer> cache = new HashMap<String, Integer>();
	
	/** 文件输入流 */
	private BufferedReader fileReader;
	
	public LogAnalysize(String file) {
		this.initFileInputStream(file);
	}
	
	/**
	 * 初始化文件输入流
	 * @param file 文件
	 */
	private void initFileInputStream(String file) {
		BufferedReader fileReader = null;
		try {
			fileReader = new BufferedReader(new FileReader(file), 4096);
			this.fileReader = fileReader;
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
		StringTokenizer tokenizer = null;
		try {
			// 分析文本
			while((line = this.fileReader.readLine()) != null) {
				tokenizer = new StringTokenizer(line, " \t");
				if(tokenizer.hasMoreTokens()) {
					if (level.equals(tokenizer.nextToken())) {
						count ++;
					}
				}
			}
			// 存入缓存
			cache.put(level, count);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 关闭日志分析器
	 */
	public void close() {
		if (fileReader != null) {
			try {
				fileReader.close();
			} catch (IOException e1) {
			}
		}
	}
	
	public static void main(String[] args) {
		int count = new LogAnalysize("1.log").countByLevel("INFO");
		System.out.println("count:" + count);
	}

}
