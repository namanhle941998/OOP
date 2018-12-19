package createdata;

import java.util.ArrayList;

public class RelationOrganizationCountry {
	public entity.Organization organization;
	public entity.Country country;
	private String relation;
	
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
