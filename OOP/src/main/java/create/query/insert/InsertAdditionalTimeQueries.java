package create.query.insert;

import java.util.ArrayList;

import create.entity.TimeGenerator;

public class InsertAdditionalTimeQueries {
	private static int additionalIDCount = 2500;// the number of additional entities created from 1 original entity
	
	public static int getAdditionalIDCount() {
		return additionalIDCount;
	}
	
	public static void setAdditionalIDCount(int value) {
		additionalIDCount = value;
	}
	
	public static String createQuery(int timeIndex, int count){
		ArrayList<entity.Time> arrayTime = new ArrayList<entity.Time>();

		ArrayList<entity.Time> arrayTimeDuplicate = new ArrayList<entity.Time>();

		arrayTime = TimeGenerator.generateTimes(count);
		for (int k = 0; k < additionalIDCount; k++) {// k = numbers of different entities, created from 1 original
														// entity, with different labels
			String ID = Integer.toString(k + 1);// ID adds to original label to create new entity
			String newname = arrayTime.get(timeIndex).getName() + "_" + ID;// new label
			entity.Time a = new entity.Time(arrayTime.get(timeIndex).getName(), newname,
					arrayTime.get(timeIndex).getDescription(), arrayTime.get(timeIndex).getLink(),
					arrayTime.get(timeIndex).getDate());
			arrayTimeDuplicate.add(k, a);
		}
		String strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < additionalIDCount; i++) {
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayTimeDuplicate.get(i).getLabel())
					+ "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayTimeDuplicate.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayTimeDuplicate.get(i).getLabel())
					+ "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayTimeDuplicate.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayTimeDuplicate.get(i).getLabel())
					+ "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayTimeDuplicate.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayTimeDuplicate.get(i).getLabel())
					+ "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayTimeDuplicate.get(i).getDate()) + "> .";
		}
		strInsert += "\n}";
		return strInsert;
	}
}
