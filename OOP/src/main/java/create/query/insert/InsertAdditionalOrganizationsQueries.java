package create.query.insert;

import java.util.ArrayList;

import create.entity.OrganizationGenerator;

public class InsertAdditionalOrganizationsQueries {
	private static int count = 100;// the number of organizations in data file
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
	
	public static String createQuery(int organizationIndex){
		ArrayList<entity.Organization> arrayOrganizations = new ArrayList<entity.Organization>();

		ArrayList<entity.Organization> arrayOrganizationsDuplicate = new ArrayList<entity.Organization>();

		arrayOrganizations = OrganizationGenerator.generateOrganizations(count, 1);// 1 means read from the 1st line in
																					// the file that contains data about
																					// countries
																					// count (=100) means read 100 lines
																					// from the 1st line

		for (int k = 0; k < additionalIDCount; k++) {// k = numbers of different entities, created from 1 original
														// entity, with different labels
			String ID = Integer.toString(k + 1);// ID adds to original label to create new entity
			String newname = arrayOrganizations.get(organizationIndex).getName() + "_" + ID;// new label
			entity.Organization a = new entity.Organization(arrayOrganizations.get(organizationIndex).getName(),
					newname, arrayOrganizations.get(organizationIndex).getDescription(),
					arrayOrganizations.get(organizationIndex).getLink(),
					arrayOrganizations.get(organizationIndex).getDate());
			arrayOrganizationsDuplicate.add(k, a);
		}
		String strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < additionalIDCount; i++) {
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getLabel())
					+ "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getLabel())
					+ "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getLabel())
					+ "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getLabel())
					+ "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getDate()) + "> .";
		}
		strInsert += "\n}";
		return strInsert;
	}
}
