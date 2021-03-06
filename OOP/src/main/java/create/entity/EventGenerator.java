package create.entity;

import java.util.ArrayList;

import create.property.DataReader;
import create.property.DateGenerator;
import create.property.LinkGenerator;

public class EventGenerator {
	private static int count = 100;// the number of events in data file
	
	public static int getCount() {
		return count;
	}
	
	public static void setCount(int value) {
		count = value;
	}
	
	public static ArrayList<entity.Event> generateEvents(int count, int start){
		ArrayList<entity.Event> events = new ArrayList<entity.Event>();
		ArrayList<String> eventsLabel = new ArrayList<String>();
		ArrayList<String> eventsName = new ArrayList<String>();
		ArrayList<String> eventsDescription = new ArrayList<String>();
		ArrayList<String> eventsLink = new ArrayList<String>();
		ArrayList<String> eventsDate = new ArrayList<String>();
		eventsLabel = DataReader.readFile("eventsLabel.txt", start, count);
		eventsName = DataReader.readFile("eventsName.txt", start, count);
		eventsDescription = DataReader.readFile("eventsDescription.txt", start, count);
		eventsLink = LinkGenerator.makeLink(count);
		eventsDate = DateGenerator.makeDate(count);
		for (int i = 0; i < count; i++) {
			entity.Event a = new entity.Event(eventsName.get(i), eventsLabel.get(i), eventsDescription.get(i), eventsLink.get(i), eventsDate.get(i));
			events.add(i, a);
		}
		return events;
	}
}
