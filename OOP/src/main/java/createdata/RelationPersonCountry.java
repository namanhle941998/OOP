package createdata;

import java.util.ArrayList;

public class RelationPersonCountry {
	public entity.Person person;
	public entity.Country country;
	private String relation;
	private static int count = 4;// the number of predicates in data file
	private static int jumpP = 4;// the number of predicates used in each query when adding relation to the database
	private static int jumpS = 50;// the number of subjects used in each query when adding relation to the database
	private static int jumpO = 50;// the number of objects used in each query when adding relation to the database
	
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
