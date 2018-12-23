package create.query.insert;

import java.util.ArrayList;

import create.relation.RelationPersonLocation;

public class InsertRelationPL {
	private static int relationCount = RelationPersonLocation.getJumpS() * RelationPersonLocation.getJumpP() * RelationPersonLocation.getJumpO();
	public static String createQuery(int startLineS, int startLineP, int startLineO) {
		ArrayList<RelationPersonLocation> arrayRelationPL = new ArrayList<RelationPersonLocation>();
		arrayRelationPL = RelationPersonLocation.createRelationPL(RelationPersonLocation.getJumpS(), startLineS,
				RelationPersonLocation.getJumpO(), startLineO, RelationPersonLocation.getJumpP(), startLineP);
		String strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < relationCount; i++) {// i will stop at (number of subjects*number of objects*number of
													// predicates), which is the number of relations created
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationPL.get(i).getPerson().getLabel())
					+ "> <http://xmlns.com/foaf/0.1/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationPL.get(i).getRelation())
					+ "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationPL.get(i).getLocation().getLabel()) + "> .";
		}
		strInsert += "\n}";
		return strInsert;
	}
}
