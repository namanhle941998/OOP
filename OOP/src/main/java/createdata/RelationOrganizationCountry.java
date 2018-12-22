package createdata;

import java.util.ArrayList;

public class RelationOrganizationCountry {
	public entity.Organization organization;
	public entity.Country country;
	private String relation;
	private static int count = 100;// the number of predicates in data file
	private static int jumpP = 100;// the number of predicates used in each query when adding relation to the database
	private static int jumpS = 10;// the number of subjects used in each query when adding relation to the database
	private static int jumpO = 10;// the number of objects used in each query when adding relation to the database
	
	public static int getCount() {
		return count;
	}
	
	public static void setCount(int value) {
		count = value;
	}
	
	public static int getJumpP() {
		return jumpP;
	}
	
	public static void setJumpP(int value) {
		jumpP = value;
	}
	
	public static int getJumpS() {
		return jumpS;
	}
	
	public static void setJumpS(int value) {
		jumpS = value;
	}
	
	public static int getJumpO() {
		return jumpO;
	}
	
	public static void setJumpO(int value) {
		jumpO = value;
	}
	
	public RelationOrganizationCountry(entity.Organization organization, entity.Country country, String relation) {
		this.organization = organization;
		this.country = country;
		this.relation = relation;
	}
	
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	public RelationOrganizationCountry() {}
	
	public void printRelation() {
		System.out.println(organization.getLabel() + " " + relation + " " + country.getLabel());
	}
	
	public static ArrayList<RelationOrganizationCountry> createRelationOC(int organizationsCount, int organizationsStart, int countriesCount, int countriesStart, int relationCount, int relationStart){
		ArrayList<String> relationOC = new ArrayList<String>();
		ArrayList<entity.Organization> organizations = new ArrayList<entity.Organization>();
		ArrayList<entity.Country> countries = new ArrayList<entity.Country>();
		ArrayList<RelationOrganizationCountry> arrayRelationOC = new ArrayList<RelationOrganizationCountry>();
		relationOC = DataReader.readFile("relationOC.txt", relationStart, relationCount);
		organizations = OrganizationGenerator.generateOrganizations(organizationsCount, organizationsStart);
		countries = CountryGenerator.generateCountries(countriesCount, countriesStart);
		for (int i = 0; i < organizationsCount; i++) {
			for (int j = 0; j < countriesCount; j++) {
				for (int k = 0; k < relationCount; k++) {
					RelationOrganizationCountry m = new RelationOrganizationCountry(organizations.get(i), countries.get(j), relationOC.get(k));
					arrayRelationOC.add(m);
				}
			}
		}
		return arrayRelationOC;
	}
}
