package create.relation;

import java.util.ArrayList;

import create.entity.EventGenerator;
import create.entity.LocationGenerator;
import create.property.DataReader;

public class RelationEventLocation {
	private entity.Event event;
	private entity.Location location;
	private String relation;
	private static int count = 1;// the number of predicates in data file
	private static int jumpP = 1;// the number of predicates used in each query when adding relation to the database
	private static int jumpS = 100;// the number of subjects used in each query when adding relation to the database
	private static int jumpO = 10;// the number of objects used in each query when adding relation to the database
	
	public static int getCount() {
		return count;
	}
	
	public static void setCount(int value) {
		count = value;
	}
	
	public static int getJumpP() {
		return jumpP;
	}
	
	public static void setJumpP(int value) {
		jumpP = value;
	}
	
	public static int getJumpS() {
		return jumpS;
	}
	
	public static void setJumpS(int value) {
		jumpS = value;
	}
	
	public static int getJumpO() {
		return jumpO;
	}
	
	public static void setJumpO(int value) {
		jumpO = value;
	}
	
	public RelationEventLocation(entity.Event event, entity.Location location, String relation) {
		this.event = event;
		this.location = location;
		this.relation = relation;
	}
	
	public entity.Event getEvent(){
		return event;
	}
	
	public entity.Location getLocation(){
		return location;
	}
	
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	public RelationEventLocation() {}
	
	public void printRelation() {
		System.out.println(event.getLabel() + " " + relation + " " + location.getLabel());
	}
	public static ArrayList<RelationEventLocation> createRelationEL(int eventsCount, int eventsStart, int locationsCount, int locationsStart, int relationCount, int relationStart){
		ArrayList<String> relationEL = new ArrayList<String>();
		ArrayList<entity.Event> events = new ArrayList<entity.Event>();
		ArrayList<entity.Location> locations = new ArrayList<entity.Location>();
		ArrayList<RelationEventLocation> arrayRelationEL = new ArrayList<RelationEventLocation>();
		relationEL = DataReader.readFile("relationEL.txt", relationStart, relationCount);
		events = EventGenerator.generateEvents(eventsCount, eventsStart);
		locations = LocationGenerator.generateLocations(locationsCount, locationsStart);
		for (int i = 0; i < eventsCount; i++) {
			for (int j = 0; j < locationsCount; j++) {
				for (int k = 0; k < relationCount; k++) {
					RelationEventLocation m = new RelationEventLocation(events.get(i), locations.get(j), relationEL.get(k));
					arrayRelationEL.add(m);
				}
			}
		}
		return arrayRelationEL;
	}
}
