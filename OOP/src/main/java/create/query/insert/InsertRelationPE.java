package create.query.insert;

import java.util.ArrayList;

import create.relation.RelationPersonEvent;

public class InsertRelationPE {
	private static int relationCount = RelationPersonEvent.getJumpS() * RelationPersonEvent.getJumpP() * RelationPersonEvent.getJumpO();
	public static String createQuery(int startLineS, int startLineP, int startLineO) {
		ArrayList<RelationPersonEvent> arrayRelationPE = new ArrayList<RelationPersonEvent>();
		arrayRelationPE = RelationPersonEvent.createRelationPE(RelationPersonEvent.getJumpS(), startLineS,
				RelationPersonEvent.getJumpO(), startLineO, RelationPersonEvent.getJumpP(), startLineP);
		String strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < relationCount; i++) {// i will stop at (number of subjects*number of objects*number of
													// predicates), which is the number of relations created
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationPE.get(i).getPerson().getLabel())
					+ "> <http://xmlns.com/foaf/0.1/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationPE.get(i).getRelation())
					+ "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationPE.get(i).getEvent().getLabel()) + "> .";
		}
		strInsert += "\n}";
		return strInsert;
	}
}
