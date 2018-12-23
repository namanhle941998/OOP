package create.query.insert;

import java.util.ArrayList;

import create.entity.PeopleGenerator;

public class InsertAdditionalPeopleQueries {
	private static int count = 50;// the number of people in data file
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
	
	public static String createQuery(int personIndex){
		ArrayList<entity.Person> arrayPeople = new ArrayList<entity.Person>();

		ArrayList<entity.Person> arrayPeopleDuplicate = new ArrayList<entity.Person>();

		arrayPeople = PeopleGenerator.generatePeople(count, 1);// 1 means read from the 1st line in the file that
																// contains data about countries
																// count (=100) means read 100 lines from the 1st line

		for (int k = 0; k < additionalIDCount; k++) {// k = numbers of different entities, created from 1 original
														// entity, with different labels
			String ID = Integer.toString(k + 1);// ID adds to original label to create new entity
			String newname = arrayPeople.get(personIndex).getName() + "_" + ID;// new label
			entity.Person a = new entity.Person(arrayPeople.get(personIndex).getName(), newname,
					arrayPeople.get(personIndex).getDescription(), arrayPeople.get(personIndex).getLink(),
					arrayPeople.get(personIndex).getDate(), arrayPeople.get(personIndex).getCareer());
			arrayPeopleDuplicate.add(k, a);
		}
		String strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < additionalIDCount; i++) {
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getLabel())
					+ "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getLabel())
					+ "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getLabel())
					+ "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getLabel())
					+ "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getDate()) + "> .";
		}
		strInsert += "\n}";
		return strInsert;
	}
}
