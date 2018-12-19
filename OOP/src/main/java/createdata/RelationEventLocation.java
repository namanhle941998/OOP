package createdata;

import java.util.ArrayList;

public class RelationEventLocation {
	public entity.Event event;
	public entity.Location location;
	private String relation;
	
	public RelationEventLocation(entity.Event event, entity.Location location, String relation) {
		this.event = event;
		this.location = location;
		this.relation = relation;
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
