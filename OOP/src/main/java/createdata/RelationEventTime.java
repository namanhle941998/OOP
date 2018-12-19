package createdata;

import java.util.ArrayList;

public class RelationEventTime {
	public entity.Event event;
	public entity.Time time;
	private String relation;
	
	public RelationEventTime(entity.Event event, entity.Time time, String relation) {
		this.event = event;
		this.time = time;
		this.relation = relation;
	}
	
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	public RelationEventTime() {}
	
	public void printRelation() {
		System.out.println(event.getLabel() + " " + relation + " " + time.getLabel());
	}
	public static ArrayList<RelationEventTime> createRelationET(int eventsCount, int eventsStart, int timesCount, int relationCount, int relationStart){
		ArrayList<String> relationET = new ArrayList<String>();
		ArrayList<entity.Event> events = new ArrayList<entity.Event>();
		ArrayList<entity.Time> times = new ArrayList<entity.Time>();
		ArrayList<RelationEventTime> arrayrelationET = new ArrayList<RelationEventTime>();
		relationET = DataReader.readFile("relationET.txt", relationStart, relationCount);
		events = EventGenerator.generateEvents(eventsCount, eventsStart);
		times = TimeGenerator.generateTimes(timesCount);
		for (int i = 0; i < eventsCount; i++) {
			for (int j = 0; j < timesCount; j++) {
				for (int k = 0; k < relationCount; k++) {
					RelationEventTime m = new RelationEventTime(events.get(i), times.get(j), relationET.get(k));
					arrayrelationET.add(m);
				}
			}
		}
		return arrayrelationET;
	}
}
