package queries;

import java.util.ArrayList;

import createdata.RelationEventTime;
import createdata.SpaceToUnderscoreConverter;

public class InsertRelationET {
	private static int timeEntityCount = 100;
	private static int relationCount = RelationEventTime.getJumpS() * RelationEventTime.getJumpP() * timeEntityCount;
	
	public static int getTimeEntityCount() {
		return timeEntityCount;
	}
	
	public static void setTimeEntityCount(int value) {
		timeEntityCount = value;
	}
	
	public static String createQuery(String strInsert, int startLineS, int startLineP) {
		ArrayList<RelationEventTime> arrayRelationET = new ArrayList<RelationEventTime>();
		arrayRelationET = RelationEventTime.createRelationET(RelationEventTime.getJumpS(), startLineS, timeEntityCount, RelationEventTime.getJumpP(), startLineP);// (number of subjects, starting line of the file containing information about subjects to be read, number of Time entity to be created, number of predicates, starting line of the file containing information about predicates to be read)
		strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < relationCount; i++) {// i will stop at (number of subjects*number of Time entity served as objects*number of predicates), which is the number of relations created
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationET.get(i).event.getLabel()) + "> <http://xmlns.com/foaf/0.1/" + SpaceToUnderscoreConverter.convert(arrayRelationET.get(i).getRelation()) + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationET.get(i).time.getLabel()) + "> .";
		}
		strInsert+= "\n}";
		return strInsert;
	}
}
