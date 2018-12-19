package createdata;

import java.util.ArrayList;

public class RelationPersonCountry {
	public entity.Person person;
	public entity.Country country;
	private String relation;
	
	public RelationPersonCountry(entity.Person person, entity.Country country, String relation) {
		this.person = person;
		this.country = country;
		this.relation = relation;
	}
	
	public String getRelation() {
		return relation;
	}
	public void setCareer(String relation) {
		this.relation = relation;
	}
	
	public RelationPersonCountry() {}
	
	public void printRelation() {
		System.out.println(person.getLabel() + " " + relation + " " + country.getLabel());
	}
	
	public static ArrayList<RelationPersonCountry> createRelationPC(int peopleCount, int peopleStart, int countriesCount, int countriesStart, int relationCount, int relationStart){
		ArrayList<String> relationPC = new ArrayList<String>();
		ArrayList<entity.Person> people = new ArrayList<entity.Person>();
		ArrayList<entity.Country> countries = new ArrayList<entity.Country>();
		ArrayList<RelationPersonCountry> arrayRelationPC = new ArrayList<RelationPersonCountry>();
		relationPC = DataReader.readFile("relationPC.txt", relationStart, relationCount);
		people = PeopleGenerator.generatePeople(peopleCount, peopleStart);
		countries = CountryGenerator.generateCountries(countriesCount, countriesStart);
		for (int i = 0; i < peopleCount; i++) {
			for (int j = 0; j < countriesCount; j++) {
				for (int k = 0; k < relationCount; k++) {
					RelationPersonCountry m = new RelationPersonCountry(people.get(i), countries.get(j), relationPC.get(k));
					arrayRelationPC.add(m);
				}
			}
		}
		return arrayRelationPC;
	}
}
