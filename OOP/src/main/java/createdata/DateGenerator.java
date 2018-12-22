package createdata;
import java.util.ArrayList;
import java.util.Random;

public class DateGenerator {
	public static ArrayList<String> makeDate(int count){
		ArrayList<String> array = new ArrayList<String>();
		Random r = new Random();
		for (int i = 1; i <= count; i++) {
			String day;
			int date = 0;
			int year = r.nextInt(39) + 1980;
			int month = r.nextInt(12) + 1;
			switch(month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12: date = r.nextInt(31) + 1;break;
			case 4:
			case 6:
			case 9:
			case 11: date = r.nextInt(30) + 1;break;
			case 2: 
				if (year % 4 == 0)
					date = r.nextInt(29) + 1;
				else
					date = r.nextInt(28) + 1;break;
			}
			day = Integer.toString(date) + "/" + Integer.toString(month) + "/" + Integer.toString(year);
			array.add(day);
		}
		return array;
	}
}
