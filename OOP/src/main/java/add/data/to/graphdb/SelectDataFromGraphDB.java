package add.data.to.graphdb;

import java.util.ArrayList;
import java.util.Scanner;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryEvaluationException;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import create.property.DataReader;
import create.query.select.BasicQueries;
import create.query.select.ComplexQueries;

public class SelectDataFromGraphDB {
	private static Logger logger = LoggerFactory.getLogger(InsertDataToGraphDB.class);

	private static final Marker WTF_MARKER = MarkerFactory.getMarker("WTF");

	private static final String GRAPHDB_SERVER = "http://localhost:7200/";
	private static String REPOSITORY_ID = "OOP";

	public static String getRepository() {
		return REPOSITORY_ID;
	}

	public static void setRepositiory(String value) {
		REPOSITORY_ID = value;
	}
	
	public static void query(RepositoryConnection repositoryConnection, String strQuery) {
		TupleQuery tupleQuery = repositoryConnection.prepareTupleQuery(QueryLanguage.SPARQL, strQuery);
		TupleQueryResult result = null;
		try {
			result = tupleQuery.evaluate();
			while (result.hasNext()) {
				BindingSet bindingSet = result.next();

				Value output = bindingSet.getValue("output");
				System.out.println(output);
			}
		} catch (QueryEvaluationException qee) {
			logger.error(WTF_MARKER, qee.getStackTrace().toString(), qee);
		} finally {
			result.close();
		}
	}
	
	public static void queryBasic(RepositoryConnection repositoryConnection, int index) {
		BasicQueries.addFromFile();
		query(repositoryConnection, BasicQueries.getQueryList().get(index - 1));
	}
	
	public static void queryComplex(RepositoryConnection repositoryConnection, int index) {
		ComplexQueries.addFromFile();
		query(repositoryConnection, ComplexQueries.getQueryList().get(index - 1));
	}
	
	public static void queryAsRequest(RepositoryConnection repositoryConnection) {
		int checkQueryType;
		int checkQueryIndex;
		System.out.println("Basic query: 1\nComplex query: 2");
		do {
			Scanner s = new Scanner(System.in);
			System.out.println("Insert the index of the type of query you want to execute: ");
			checkQueryType = s.nextInt();
		}
		while(checkQueryType <= 0 || checkQueryType > 2);
		switch(checkQueryType) {
			case 1: 
				ArrayList<String> basicQueryList = DataReader.readFile("basicQueriesInEnglish.txt", 1, BasicQueries.getQueryCount());
				for (int i = 0; i < BasicQueries.getQueryCount(); i++) {
					System.out.println(basicQueryList.get(i));
				}
				break;
			case 2:
				ArrayList<String> complexQueryList = DataReader.readFile("complexQueriesInEnglish.txt", 1, BasicQueries.getQueryCount());
				for (int i = 0; i < BasicQueries.getQueryCount(); i++) {
					System.out.println(complexQueryList.get(i));
				}
				break;
			default:
				break;
		}
		do {
			Scanner s = new Scanner(System.in);
			System.out.println("Insert the index of the query you want to execute: ");
			checkQueryIndex = s.nextInt();
			s.close();
		}
		while (checkQueryIndex <= 0 || checkQueryIndex >= 11);
		if (checkQueryType == 1) {
			queryBasic(repositoryConnection, checkQueryIndex);
		}
		else {
			queryComplex(repositoryConnection, checkQueryIndex);
		}
	}
}
