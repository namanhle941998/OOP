package create.relation;

import java.util.ArrayList;

import create.entity.CountryGenerator;
import create.entity.EventGenerator;
import create.property.DataReader;

public class RelationCountryEvent {
	private entity.Country country;
	private entity.Event event;
	private String relation;
	private static int count = 1;// the number of predicates in data file
	private static int jumpP = 1;// the number of predicates used in each query when adding relation to the database
	private static int jumpS = 100;// the number of subjects used in each query when adding relation to the database
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
	
	public RelationCountryEvent(entity.Country country, entity.Event event, String relation) {
		this.country = country;
		this.event = event;
		this.relation = relation;
	}
	
	public entity.Country getCountry(){
		return country;
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
	
	public RelationCountryEvent() {}
	
	public void printRelation() {
		System.out.println(country.getLabel() + " " + relation + " " + event.getLabel());
	}
	
	public static ArrayList<RelationCountryEvent> createRelationCE(int countriesCount, int countriesStart, int eventsCount, int eventsStart, int relationCount, int relationStart){
		ArrayList<String> relationCE = new ArrayList<String>();
		ArrayList<entity.Country> countries = new ArrayList<entity.Country>();
		ArrayList<entity.Event> events = new ArrayList<entity.Event>();
		ArrayList<RelationCountryEvent> arrayRelationCE = new ArrayList<RelationCountryEvent>();
		relationCE = DataReader.readFile("relationCE.txt", relationStart, relationCount);
		countries = CountryGenerator.generateCountries(countriesCount, countriesStart);
		events = EventGenerator.generateEvents(eventsCount, eventsStart);
		for (int i = 0; i < countriesCount; i++) {
			for (int j = 0; j < eventsCount; j++) {
				for (int k = 0; k < relationCount; k++) {
					RelationCountryEvent m = new RelationCountryEvent(countries.get(i), events.get(j), relationCE.get(k));
					arrayRelationCE.add(m);
				}
			}
		}
		return arrayRelationCE;
	}
}
