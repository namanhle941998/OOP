package create.relation;

import java.util.ArrayList;

import create.entity.LocationGenerator;
import create.entity.PeopleGenerator;
import create.property.DataReader;

public class RelationPersonLocation {
	private entity.Person person;
	private entity.Location location;
	private String relation;
	private static int count = 5;// the number of predicates in data file
	private static int jumpP = 5;// the number of predicates used in each query when adding relation to the database
	private static int jumpS = 50;// the number of subjects used in each query when adding relation to the database
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
	
	public RelationPersonLocation(entity.Person person, entity.Location location, String relation) {
		this.person = person;
		this.location = location;
		this.relation = relation;
	}
	
	public entity.Person getPerson(){
		return person;
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
	
	public RelationPersonLocation() {}
	
	public void printRelation() {
		System.out.println(person.getLabel() + " " + relation + " " + location.getLabel());
	}
	
	public static ArrayList<RelationPersonLocation> createRelationPL(int peopleCount, int peopleStart, int locationsCount, int locationsStart, int relationCount, int relationStart){
		ArrayList<String> relationPL = new ArrayList<String>();
		ArrayList<entity.Person> people = new ArrayList<entity.Person>();
		ArrayList<entity.Location> locations = new ArrayList<entity.Location>();
		ArrayList<RelationPersonLocation> arrayRelationPL = new ArrayList<RelationPersonLocation>();
		relationPL = DataReader.readFile("relationPL.txt", relationStart, relationCount);
		people = PeopleGenerator.generatePeople(peopleCount, peopleStart);
		locations = LocationGenerator.generateLocations(locationsCount, locationsStart);
		for (int i = 0; i < peopleCount; i++) {
			for (int j = 0; j < locationsCount; j++) {
				for (int k = 0; k < relationCount; k++) {
					RelationPersonLocation m = new RelationPersonLocation(people.get(i), locations.get(j), relationPL.get(k));
					arrayRelationPL.add(m);
				}
			}
		}
		return arrayRelationPL;
	}
}
