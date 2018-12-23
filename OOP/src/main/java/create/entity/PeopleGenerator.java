package create.entity;

import java.util.ArrayList;

import create.property.DataReader;
import create.property.DateGenerator;
import create.property.LinkGenerator;

public class PeopleGenerator {
	private static int count = 50;// the number of people in data file
	
	public static int getCount() {
		return count;
	}
	
	public static void setCount(int value) {
		count = value;
	}
	
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
