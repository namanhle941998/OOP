package queries;

import java.util.ArrayList;

import createdata.EventGenerator;
import createdata.SpaceToUnderscoreConverter;

public class InsertAdditionalEventsQueries {
	private static int count = 100;// the number of events in data file
	private static int additionalIDCount = 2500;// the number of additional entities created from 1 original entity
	
	public static int getCount() {
		return count;
	}
	
	public static void setCount(int value) {
		count = value;
	}
	
	public static int getAdditionalIDCount() {
		return additionalIDCount;
	}
	
	public static void setAdditionalIDCount(int value) {
		additionalIDCount = value;
	}
	
	public static String createQuery(String strInsert, int eventIndex){
		ArrayList<entity.Event> arrayEvents = new ArrayList<entity.Event>();
		
		ArrayList<entity.Event> arrayEventsDuplicate = new ArrayList<entity.Event>();
		
		arrayEvents = EventGenerator.generateEvents(count, 1);// 1 means read from the 1st line in the file that contains data about countries
															  // count (=100) means read 100 lines from the 1st line
		
		for (int k = 0; k < additionalIDCount; k++) {// k = numbers of different entities, created from 1 original entity, with different labels
			String ID = Integer.toString(k + 1);// ID adds to original label to create new entity
			String newname = arrayEvents.get(eventIndex).getName() + "_" + ID;// new label
			entity.Event a = new entity.Event(arrayEvents.get(eventIndex).getName(), newname, arrayEvents.get(eventIndex).getDescription(), arrayEvents.get(eventIndex).getLink(), arrayEvents.get(eventIndex).getDate());
			arrayEventsDuplicate.add(k, a);
		}
		strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < additionalIDCount; i++) {
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getDate()) + "> .";
		}
		strInsert+= "\n}";
		return strInsert;
	}
}
