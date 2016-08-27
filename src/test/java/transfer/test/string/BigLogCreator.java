package transfer.test.string;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BigLogCreator {

	static String[] levels = new String[]{"INFO", "ERROR", "WARN", "DEBUG"};
	
	public static void main(String[] args) throws IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("1.log"));
		for (int i = 0; i< 100000;i++) {
			writer.write(randomLevel());
			writer.write('\t');
			writer.write("{\"clientId\": \"guidxx_1\", \"title\": \"测试宝贝01\", \"price\": 10.0, \"quantity\": 100, \"location\": \"杭州\", \"isFreeShip\": false, \"telephone\": \"16354745843\"}");
			writer.newLine();
		}
		
		writer.close();
		
	}
	
	static String randomLevel() {
		return levels[new Random().nextInt(4)];
	}
	
}
