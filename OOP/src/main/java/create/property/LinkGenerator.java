package create.property;
import java.util.ArrayList;
import java.util.Random;

public class LinkGenerator {
	public static ArrayList<String> makeLink(int count) {
		char c[] = new char[62];
		ArrayList<String> array = new ArrayList<String>();
		for (int i = 0; i <= 9; i++) {
			c[i] = (char)(i + 48);
		}
		for (int i = 10; i <= 35; i++) {
			c[i] = (char)(i + 55);
		}
		for (int i = 36; i <= 61; i++) {
			c[i] = (char)(i + 61);
		}
		for (int i = 1; i <= count; i++) {
			String link = "goo.gl/";
			for (int j = 1; j <= 6; j++) {
				Random r = new Random();
				int number = r.nextInt(62);
				link += c[number];
			}
			array.add(link);
		}
		return array;
	}
}
