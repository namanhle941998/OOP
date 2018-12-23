package create.relation;

import java.util.ArrayList;

import create.entity.OrganizationGenerator;
import create.entity.TimeGenerator;
import create.property.DataReader;

public class RelationOrganizationTime {
	private entity.Organization organization;
	private entity.Time time;
	private String relation;
	private static int count = 100;// the number of predicates in data file
	private static int countO = 100;// the number of objects (which is Time entity) to be created
	private static int jumpP = 10;// the number of predicates used in each query when adding relation to the database
	private static int jumpS = 10;// the number of subjects used in each query when adding relation to the database
	
	public static int getCount() {
		return count;
	}
	
	public static void setCount(int value) {
		count = value;
	}
	
	public static int getCountO() {
		return countO;
	}
	
	public static void setCountO(int value) {
		countO = value;
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
	
	public RelationOrganizationTime(entity.Organization organization, entity.Time time, String relation) {
		this.organization = organization;
		this.time = time;
		this.relation = relation;
	}
	
	public entity.Organization getOrganization(){
		return organization;
	}
	
	public entity.Time getTime(){
		return time;
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
