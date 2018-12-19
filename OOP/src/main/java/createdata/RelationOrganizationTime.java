package createdata;

import java.util.ArrayList;

public class RelationOrganizationTime {
	public entity.Organization organization;
	public entity.Time time;
	private String relation;
	
	public RelationOrganizationTime(entity.Organization organization, entity.Time time, String relation) {
		this.organization = organization;
		this.time = time;
		this.relation = relation;
	}
	
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	public RelationOrganizationTime() {}
	
	public void printRelation() {
		System.out.println(organization.getLabel() + " " + relation + " " + time.getLabel());
	}
	
	public static ArrayList<RelationOrganizationTime> createRelationOT(int organizationsCount, int organizationsStart, int timesCount, int relationCount, int relationStart){
		ArrayList<String> relationOT = new ArrayList<String>();
		ArrayList<entity.Organization> organizations = new ArrayList<entity.Organization>();
		ArrayList<entity.Time> times = new ArrayList<entity.Time>();
		ArrayList<RelationOrganizationTime> arrayrelationOT = new ArrayList<RelationOrganizationTime>();
		relationOT = DataReader.readFile("relationOT.txt", relationStart, relationCount);
		organizations = OrganizationGenerator.generateOrganizations(organizationsCount, organizationsStart);
		times = TimeGenerator.generateTimes(timesCount);
		for (int i = 0; i < organizationsCount; i++) {
			for (int j = 0; j < timesCount; j++) {
				for (int k = 0; k < relationCount; k++) {
					RelationOrganizationTime m = new RelationOrganizationTime(organizations.get(i), times.get(j), relationOT.get(k));
					arrayrelationOT.add(m);
				}
			}
		}
		return arrayrelationOT;
	}
}
