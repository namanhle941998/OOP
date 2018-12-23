package create.query.insert;

import java.util.ArrayList;

import create.relation.RelationPersonCountry;

public class InsertRelationPC {
	private static int relationCount = RelationPersonCountry.getJumpS() * RelationPersonCountry.getJumpP() * RelationPersonCountry.getJumpO();
	public static String createQuery(int startLineS, int startLineP, int startLineO) {
		ArrayList<RelationPersonCountry> arrayRelationPC = new ArrayList<RelationPersonCountry>();
		arrayRelationPC = RelationPersonCountry.createRelationPC(RelationPersonCountry.getJumpS(), startLineS,
				RelationPersonCountry.getJumpO(), startLineO, RelationPersonCountry.getJumpP(), startLineP);
		String strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < relationCount; i++) {// i will stop at (number of subjects*number of objects*number of
													// predicates), which is the number of relations created
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationPC.get(i).getPerson().getLabel())
					+ "> <http://xmlns.com/foaf/0.1/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationPC.get(i).getRelation())
					+ "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationPC.get(i).getCountry().getLabel()) + "> .";
		}
		strInsert += "\n}";
		return strInsert;
	}
}
