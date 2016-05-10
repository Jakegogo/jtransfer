package transfer.test.string;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BigLogCreator {

	static String[] levels = new String[]{"INFO", "ERROR", "WARN", "DEBUG"};
	
	public static void main(String[] args) throws IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("1.log"));
		
		for (int i = 0; i< 100000000;i++) {
			writer.write(randomLevel());
			writer.write('\t');
			writer.write("1234567890");
			writer.newLine();
		}
		
		writer.close();
		
	}
	
	static String randomLevel() {
		return levels[new Random().nextInt(4)];
	}
	
}
