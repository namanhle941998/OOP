package queries;

import java.util.ArrayList;

import createdata.CountryGenerator;
import createdata.EventGenerator;
import createdata.LocationGenerator;
import createdata.OrganizationGenerator;
import createdata.PeopleGenerator;
import createdata.SpaceToUnderscoreConverter;

public class InsertOriginalEntitiesQueries {
	public static String createQuery(String strInsert){
		ArrayList<entity.Country> arrayCountries = new ArrayList<entity.Country>();
		ArrayList<entity.Event> arrayEvents = new ArrayList<entity.Event>();
		ArrayList<entity.Person> arrayPeople = new ArrayList<entity.Person>();
		ArrayList<entity.Organization> arrayOrganizations = new ArrayList<entity.Organization>();
		ArrayList<entity.Location> arrayLocations = new ArrayList<entity.Location>();
		
		arrayOrganizations = OrganizationGenerator.generateOrganizations(100, 1);// 1 means read from the 1st line in the file that contains data about organization
		arrayCountries = CountryGenerator.generateCountries(100, 1);			 // 100 means read 100 lines from the 1st line
		arrayPeople = PeopleGenerator.generatePeople(50, 1);
		arrayLocations = LocationGenerator.generateLocations(10, 1);
		arrayEvents = EventGenerator.generateEvents(100, 1);
		
		strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < 100; i++) {
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getDate()) + "> .";
		}
		
		
		for (int i = 0; i < 100; i++) {
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getDate()) + "> .";
		}
		
		for (int i = 0; i < 50; i++) {
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getDate()) + "> .";
		}
		
		for (int i = 0; i < 10; i++) {
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getDate()) + "> .";
		}
		
		for (int i = 0; i < 100; i++) {
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getDate()) + "> .";
		}
		strInsert+= "\n}";
		
		return strInsert;
	}
}
