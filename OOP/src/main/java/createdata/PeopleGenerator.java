package createdata;

import java.util.ArrayList;

public class PeopleGenerator {
	public static ArrayList<entity.Person> generatePeople(int count, int start){
		ArrayList<entity.Person> people = new ArrayList<entity.Person>();
		ArrayList<String> peopleLabel = new ArrayList<String>();
		ArrayList<String> peopleName = new ArrayList<String>();
		ArrayList<String> peopleDescription = new ArrayList<String>();
		ArrayList<String> peopleLink = new ArrayList<String>();
		ArrayList<String> peopleDate = new ArrayList<String>();
		ArrayList<String> peopleCareer = new ArrayList<String>();
		peopleLabel = DataReader.readFile("personLabel.txt", start, count);
		peopleName = DataReader.readFile("personName.txt", start, count);
		peopleDescription = DataReader.readFile("personDescription.txt", start, count);
		peopleLink = LinkGenerator.makeLink(count);
		peopleDate = DateGenerator.makeDate(count);
		peopleCareer = DataReader.readFile("personCareer.txt", start, count);
		for (int i = 0; i < count; i++) {
			entity.Person a = new entity.Person(peopleName.get(i), peopleLabel.get(i), peopleDescription.get(i), peopleLink.get(i), peopleDate.get(i), peopleCareer.get(i));
			people.add(i, a);
		}
		return people;
	}
}