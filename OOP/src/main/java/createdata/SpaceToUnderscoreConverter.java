package createdata;

public class SpaceToUnderscoreConverter {
	public static String convert(String string){
		return string.replaceAll(" ", "_");
	}
}
