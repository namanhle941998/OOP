package create.query.select;

import java.util.ArrayList;

import create.property.DataReader;

public class BasicQueries {
	private static ArrayList<String> query;
	private static int queryCount = 10;
	
	public static int getQueryCount() {
		return queryCount;
	}
	
	public static void setQueryCount(int value) {
		queryCount = value;
	}
	
	public static ArrayList<String> getQueryList(){
		return query;
	}
	
	public static void add(String newQuery) {
		query.add(newQuery);
	}
	
	public static void addFromFile() {
		ArrayList<String> arrayQueries = DataReader.readFile("basicQueries.txt", 1, queryCount);
		query = arrayQueries;
	}
}
