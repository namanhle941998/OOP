package create.query.insert;

import java.util.ArrayList;

import create.relation.RelationOrganizationCountry;

public class InsertRelationOC {
	private static int relationCount = RelationOrganizationCountry.getJumpS() * RelationOrganizationCountry.getJumpP() * RelationOrganizationCountry.getJumpO();
	public static String createQuery(int startLineS, int startLineP, int startLineO) {
		ArrayList<RelationOrganizationCountry> arrayRelationOC = new ArrayList<RelationOrganizationCountry>();
		arrayRelationOC = RelationOrganizationCountry.createRelationOC(RelationOrganizationCountry.getJumpS(),
				startLineS, RelationOrganizationCountry.getJumpO(), startLineO, RelationOrganizationCountry.getJumpP(),
				startLineP);
		String strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < relationCount; i++) {// i will stop at (number of subjects*number of objects*number of
													// predicates), which is the number of relations created
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationOC.get(i).getOrganization().getLabel())
					+ "> <http://xmlns.com/foaf/0.1/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationOC.get(i).getRelation())
					+ "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationOC.get(i).getCountry().getLabel()) + "> .";
		}
		strInsert += "\n}";
		return strInsert;
	}
}
