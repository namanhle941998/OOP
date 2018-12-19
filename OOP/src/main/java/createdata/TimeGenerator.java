package createdata;

import java.util.ArrayList;

public class TimeGenerator {
	public static ArrayList<entity.Time> generateTimes(int count){
		ArrayList<entity.Time> times = new ArrayList<entity.Time>();
		ArrayList<String> timesLabel = new ArrayList<String>();
		ArrayList<String> timesName = new ArrayList<String>();
		ArrayList<String> timesDescription = new ArrayList<String>();
		ArrayList<String> timesLink = new ArrayList<String>();
		ArrayList<String> timesDate = new ArrayList<String>();
		timesLabel = DateGenerator.makeDate(count);
		timesName = timesLabel;
		timesDescription = timesLabel;
		timesLink = LinkGenerator.makeLink(count);
		timesDate = timesLabel;
		for (int i = 0; i < count; i++) {
			entity.Time a = new entity.Time(timesName.get(i), timesLabel.get(i), timesDescription.get(i), timesLink.get(i), timesDate.get(i));
			times.add(i, a);
		}
		return times;
	}
}
