package transfer.test.string;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 敏感词分析
 * @author Administrator
 *
 */
public class WordSearch {

	/**
	 * 敏感词文件
	 */
	private String sensitiveWordFile = "1.txt";
	
	/**
	 * 敏感词集合
	 */
	private List<String> sensitiveWords;
	
	
	public WordSearch(){
		this.initSensitiveWords2();
	}
	
	/**
	 * 初始化敏感词集合
	 */
	private void initSensitiveWords() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(this.sensitiveWordFile));
			
			List<String> sensitiveWords = new ArrayList<String>();
			String word = null;
			while((word = reader.readLine()) != null) {
				sensitiveWords.add(word);
			}
			this.sensitiveWords = sensitiveWords;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}
		}
	}
	
	
	/**
	 * 初始化敏感词集合
	 */
	private void initSensitiveWords2() {
		DataInputStream dataInputStream = null;
		try {
			dataInputStream = new DataInputStream(new BufferedInputStream(
					new FileInputStream(this.sensitiveWordFile)));

			List<String> sensitiveWords = new ArrayList<String>();
			String word = null;
			while ((word = dataInputStream.readLine()) != null) {
				sensitiveWords.add(word);
			}
			this.sensitiveWords = sensitiveWords;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dataInputStream != null) {
				try {
					dataInputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}


	/**
	 * 检测是否包含敏感词
	 * @param contents 文本内容
	 * @return
	 */
	public boolean hasDensitiveWords(List<String> contents) {
		
		for (String content: contents) {
			for (String word : this.sensitiveWords) {
				if (content.indexOf(word) > -1) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	public static void main(String[] args) {
		boolean detected = new WordSearch().hasDensitiveWords(Arrays.asList("TMD1","2"));
		System.out.println("has sensitive words : " + detected);
	}
	
}
