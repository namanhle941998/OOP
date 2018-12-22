package createdata;

import java.util.ArrayList;

public class RelationPersonLocation {
	public entity.Person person;
	public entity.Location location;
	private String relation;
	
	public RelationPersonLocation(entity.Person person, entity.Location location, String relation) {
		this.person = person;
		this.location = location;
		this.relation = relation;
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
