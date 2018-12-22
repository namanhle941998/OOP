package createdata;

import java.util.ArrayList;

public class RelationCountryEvent {
	public entity.Country country;
	public entity.Event event;
	private String relation;
	
	public RelationCountryEvent(entity.Country country, entity.Event event, String relation) {
		this.country = country;
		this.event = event;
		this.relation = relation;
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
