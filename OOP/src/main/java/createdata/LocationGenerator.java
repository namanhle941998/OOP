package createdata;

import java.util.ArrayList;

public class LocationGenerator {
	public static ArrayList<entity.Location> generateLocations(int count, int start){
		ArrayList<entity.Location> locations = new ArrayList<entity.Location>();
		ArrayList<String> locationsLabel = new ArrayList<String>();
		ArrayList<String> locationsName = new ArrayList<String>();
		ArrayList<String> locationsDescription = new ArrayList<String>();
		ArrayList<String> locationsLink = new ArrayList<String>();
		ArrayList<String> locationsDate = new ArrayList<String>();
		locationsLabel = DataReader.readFile("LocationLabel.txt", start, count);
		locationsName = DataReader.readFile("LocationName.txt", start, count);
		locationsDescription = DataReader.readFile("LocationDescription.txt", start, count);
		locationsLink = LinkGenerator.makeLink(count);
		locationsDate = DateGenerator.makeDate(count);
		for (int i = 0; i < count; i++) {
			entity.Location a = new entity.Location(locationsName.get(i), locationsLabel.get(i), locationsDescription.get(i), locationsLink.get(i), locationsDate.get(i));
			locations.add(i, a);
		}
		return locations;
	}
}
