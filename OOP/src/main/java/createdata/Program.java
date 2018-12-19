package createdata;
import java.util.ArrayList;

public class Program {

	public static void main(String[] args) {
		String strInsert;
		ArrayList<entity.Person> arrayPeople = new ArrayList<entity.Person>();
		ArrayList<entity.Organization> arrayOrganizations = new ArrayList<entity.Organization>();
		ArrayList<entity.Organization> arrayOrganizationsDuplicate = new ArrayList<entity.Organization>();
		arrayOrganizations = OrganizationGenerator.generateOrganizations(100, 1);
		int j = 0;
		for (int k = 0; k < 10000; k++) {
			String ID = Integer.toString(k + 1);
			String newname = arrayOrganizations.get(0).getName() + "_" + ID;
			entity.Organization a = new entity.Organization(arrayOrganizations.get(j).getName(), newname, arrayOrganizations.get(j).getDescription(), arrayOrganizations.get(j).getLink(), arrayOrganizations.get(j).getDate());
			arrayOrganizationsDuplicate.add(k, a);
		}
		System.out.println(arrayOrganizationsDuplicate.get(0).getLabel());
	}
}
