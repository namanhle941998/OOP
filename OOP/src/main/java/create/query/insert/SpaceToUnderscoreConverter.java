package create.query.insert;

public class SpaceToUnderscoreConverter {
	public static String convert(String string){
		return string.replaceAll(" ", "_");
	}
}
