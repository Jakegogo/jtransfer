package transfer.test.string;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 日志分析
 * @author Administrator
 *
 */
public class BigLogAnalysize {
	
	/** 错误类型统计数量,结果缓存 */
	private Map<String, Integer> cache = new HashMap<String, Integer>();
	
	/** 文件扫描器 */
	private Scanner fileScanner;
	/** 文件输入流 */
	private FileInputStream inputStream;

	
	public BigLogAnalysize(String file) {
		this.initFileInputStream(file);
	}
	
	/**
	 * 初始化文件输入流
	 * @param file 文件
	 */
	private void initFileInputStream(String file) {
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(file);
		    sc = new Scanner(inputStream);
		    
			this.inputStream = inputStream;
			this.fileScanner = sc;
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
		
		int lineNum = 0;
		// 分析文本
		while(this.fileScanner.hasNextLine()) {
			line = this.fileScanner.nextLine();
			tokenizer = new StringTokenizer(line, " \t");
			if(tokenizer.hasMoreTokens()) {
				if (level.equals(tokenizer.nextToken())) {
					count ++;
				}
			}
			if (++lineNum % 1000000 == 0) {
				System.out.println(lineNum);
			}
		}
		// 存入缓存
		cache.put(level, count);
	
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
		if (fileScanner != null) {
			fileScanner.close();
		}
	}
	
	public static void main(String[] args) {
		int count = new BigLogAnalysize("1.log").countByLevel("INFO");
		System.out.println("count:" + count);
	}

}
