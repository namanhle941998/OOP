package queries;

import java.util.ArrayList;

import createdata.CountryGenerator;
import createdata.SpaceToUnderscoreConverter;

public class InsertAdditionalCountriesQueries {
	private static int count = 100;// the number of countries in data file
	private static int additionalIDCount = 2500;// the number of additional entities created from 1 original entity
	
	public static int getCount() {
		return count;
	}
	
	public static void setCount(int value) {
		count = value;
	}
	
	public static int getAdditionalIDCount() {
		return additionalIDCount;
	}
	
	public static void setAdditionalIDCount(int value) {
		additionalIDCount = value;
	}
	
	public static String createQuery(String strInsert, int countryIndex){
		ArrayList<entity.Country> arrayCountries = new ArrayList<entity.Country>();
		
		ArrayList<entity.Country> arrayCountriesDuplicate = new ArrayList<entity.Country>();
		
		arrayCountries = CountryGenerator.generateCountries(count, 1);// 1 means read from the 1st line in the file that contains data about countries
																	  // count (=100) means read 100 lines from the 1st line
		
		for (int k = 0; k < additionalIDCount; k++) {// k = numbers of different entities, created from 1 original entity, with different labels
			String ID = Integer.toString(k + 1);// ID adds to original label to create new entity
			String newname = arrayCountries.get(countryIndex).getName() + "_" + ID;// new label
			entity.Country a = new entity.Country(arrayCountries.get(countryIndex).getName(), newname, arrayCountries.get(countryIndex).getDescription(), arrayCountries.get(countryIndex).getLink(), arrayCountries.get(countryIndex).getDate());
			arrayCountriesDuplicate.add(k, a);
		}
		strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < additionalIDCount; i++) {
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getDate()) + "> .";
		}
		strInsert+= "\n}";
		return strInsert;
	}
}
