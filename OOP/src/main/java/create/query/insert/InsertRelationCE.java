package create.query.insert;

import java.util.ArrayList;

import create.relation.RelationCountryEvent;

public class InsertRelationCE {
	private static int relationCount = RelationCountryEvent.getJumpS() * RelationCountryEvent.getJumpP() * RelationCountryEvent.getJumpO();
	
	public static String createQuery(int startLineS, int startLineP, int startLineO) {
		ArrayList<RelationCountryEvent> arrayRelationCE = new ArrayList<RelationCountryEvent>();
		arrayRelationCE = RelationCountryEvent.createRelationCE(RelationCountryEvent.getJumpS(), startLineS,
				RelationCountryEvent.getJumpO(), startLineO, RelationCountryEvent.getJumpP(), startLineP);
		String strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < relationCount; i++) {// i will stop at (number of subjects*number of objects*number of
													// predicates), which is the number of relations created
			strInsert += "\n<http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationCE.get(i).getCountry().getLabel())
					+ "> <http://xmlns.com/foaf/0.1/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationCE.get(i).getRelation())
					+ "> <http://dbpedia.org/resource/"
					+ SpaceToUnderscoreConverter.convert(arrayRelationCE.get(i).getEvent().getLabel()) + "> .";
		}
		strInsert += "\n}";
		return strInsert;
	}
}
