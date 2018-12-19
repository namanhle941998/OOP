package createdata;

import java.util.ArrayList;

public class CountryGenerator {
	public static ArrayList<entity.Country> generateCountries(int count, int start){
		ArrayList<entity.Country> countries = new ArrayList<entity.Country>();
		ArrayList<String> countriesLabel = new ArrayList<String>();
		ArrayList<String> countriesName = new ArrayList<String>();
		ArrayList<String> countriesDescription = new ArrayList<String>();
		ArrayList<String> countriesLink = new ArrayList<String>();
		ArrayList<String> countriesDate = new ArrayList<String>();
		countriesLabel = DataReader.readFile("CountryLabel.txt", start, count);
		countriesName = DataReader.readFile("CountryName.txt", start, count);
		countriesDescription = DataReader.readFile("CountryDescription.txt", start, count);
		countriesLink = LinkGenerator.makeLink(count);
		countriesDate = DateGenerator.makeDate(count);
		for (int i = 0; i < count; i++) {
			entity.Country a = new entity.Country(countriesName.get(i), countriesLabel.get(i), countriesDescription.get(i), countriesLink.get(i), countriesDate.get(i));
			countries.add(i, a);
		}
		return countries;
	}
}
