package create.entity;

import java.util.ArrayList;

import create.property.DataReader;
import create.property.DateGenerator;
import create.property.LinkGenerator;

public class OrganizationGenerator {
	private static int count = 100;// the number of organizations in data file
	
	public static int getCount() {
		return count;
	}
	
	public static void setCount(int value) {
		count = value;
	}
	
	public static ArrayList<entity.Organization> generateOrganizations(int count, int start){
		ArrayList<entity.Organization> organizations = new ArrayList<entity.Organization>();
		ArrayList<String> organizationsLabel = new ArrayList<String>();
		ArrayList<String> organizationsName = new ArrayList<String>();
		ArrayList<String> organizationsDescription = new ArrayList<String>();
		ArrayList<String> organizationsLink = new ArrayList<String>();
		ArrayList<String> organizationsDate = new ArrayList<String>();
		organizationsLabel = DataReader.readFile("organizationLabel.txt", start, count);
		organizationsName = DataReader.readFile("organizationName.txt", start, count);
		organizationsDescription = DataReader.readFile("organizationDescription.txt", start, count);
		organizationsLink = LinkGenerator.makeLink(count);
		organizationsDate = DateGenerator.makeDate(count);
		for (int i = 0; i < count; i++) {
			entity.Organization a = new entity.Organization(organizationsName.get(i), organizationsLabel.get(i), organizationsDescription.get(i), organizationsLink.get(i), organizationsDate.get(i));
			organizations.add(i, a);
		}
		return organizations;
	}
}
