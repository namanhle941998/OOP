package create.query.insert;

import java.util.ArrayList;

import create.relation.RelationEventLocation;

public class InsertRelationEL {
	private static int relationCount = RelationEventLocation.getJumpS() * RelationEventLocation.getJumpP() * RelationEventLocation.getJumpO();
	public static String createQuery(int startLineS, int startLineP, int startLineO) {
		ArrayList<RelationEventLocation> arrayRelationEL = new ArrayList<RelationEventLocation>();
		arrayRelationEL = RelationEventLocation.createRelationEL(RelationEventLocation.getJumpS(), startLineS,
				RelationEventLocation.getJumpO(), startLineO, RelationEventLocation.getJumpP(), startLineP);
		String strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < relationCount; i++) {// i will stop at (number of subjects*number of objects*number of
													// predicates), which is the number of relations created
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationEL.get(i).getEvent().getLabel())
					+ "> <http://xmlns.com/foaf/0.1/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationEL.get(i).getRelation())
					+ "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationEL.get(i).getLocation().getLabel()) + "> .";
		}
		strInsert += "\n}";
		return strInsert;
	}
}
