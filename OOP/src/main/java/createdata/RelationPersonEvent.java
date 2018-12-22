package createdata;

import java.util.ArrayList;

public class RelationPersonEvent {
	public entity.Person person;
	public entity.Event event;
	private String relation;
	
	public RelationPersonEvent(entity.Person person, entity.Event event, String relation) {
		this.person = person;
		this.event = event;
		this.relation = relation;
	}
	
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	public RelationPersonEvent() {}
	
	public void printRelation() {
		System.out.println(person.getLabel() + " " + relation + " " + event.getLabel());
	}
	
	public static ArrayList<RelationPersonEvent> createRelationPE(int peopleCount, int peopleStart, int eventsCount, int eventsStart, int relationCount, int relationStart){
		ArrayList<String> relationPE = new ArrayList<String>();
		ArrayList<entity.Person> people = new ArrayList<entity.Person>();
		ArrayList<entity.Event> events = new ArrayList<entity.Event>();
		ArrayList<RelationPersonEvent> arrayRelationPE = new ArrayList<RelationPersonEvent>();
		relationPE = DataReader.readFile("relationPE.txt", relationStart, relationCount);
		people = PeopleGenerator.generatePeople(peopleCount, peopleStart);
		events = EventGenerator.generateEvents(eventsCount, eventsStart);
		for (int i = 0; i < peopleCount; i++) {
			for (int j = 0; j < eventsCount; j++) {
				for (int k = 0; k < relationCount; k++) {
					RelationPersonEvent m = new RelationPersonEvent(people.get(i), events.get(j), relationPE.get(k));
					arrayRelationPE.add(m);
				}
			}
		}
		return arrayRelationPE;
	}
}
