package create.relation;

import java.util.ArrayList;

import create.entity.EventGenerator;
import create.entity.PeopleGenerator;
import create.property.DataReader;

public class RelationPersonEvent {
	private entity.Person person;
	private entity.Event event;
	private String relation;
	private static int count = 1;// the number of predicates in data file
	private static int jumpP = 1;// the number of predicates used in each query when adding relation to the database
	private static int jumpS = 50;// the number of subjects used in each query when adding relation to the database
	private static int jumpO = 100;// the number of objects used in each query when adding relation to the database
	
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
	
	public RelationPersonEvent(entity.Person person, entity.Event event, String relation) {
		this.person = person;
		this.event = event;
		this.relation = relation;
	}
	
	public entity.Person getPerson(){
		return person;
	}
	
	public entity.Event getEvent(){
		return event;
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
