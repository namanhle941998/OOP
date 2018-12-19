package GraphDB.OOP;

import java.util.ArrayList;

import org.eclipse.rdf4j.model.impl.SimpleLiteral;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryEvaluationException;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.query.Update;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import createdata.CountryGenerator;
import createdata.EventGenerator;
import createdata.LocationGenerator;
import createdata.OrganizationGenerator;
import createdata.PeopleGenerator;
import createdata.RelationEventLocation;
import createdata.RelationEventTime;
import createdata.RelationOrganizationCountry;
import createdata.RelationPersonCountry;
import createdata.RelationOrganizationTime;
import createdata.SpaceToUnderscoreConverter;

public class SimpleInsertQueryExample {
	private static Logger logger = LoggerFactory.getLogger(SimpleInsertQueryExample.class);
	// Why This Failure marker
	private static final Marker WTF_MARKER = MarkerFactory.getMarker("WTF");
  
	// GraphDB 
	private static final String GRAPHDB_SERVER = "http://localhost:7200/";
	private static final String REPOSITORY_ID = "OOP";


	private static String strInsert;
	private static String strQuery;
  
	static {
		strInsert = "";
    
		strQuery = "";
	}  
  
	private static RepositoryConnection getRepositoryConnection() {
	    Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
	    repository.initialize();
	    RepositoryConnection repositoryConnection = repository.getConnection();
	    return repositoryConnection;
	}
  
	private static void insert(RepositoryConnection repositoryConnection) {
	    repositoryConnection.begin();
	    
	    Update updateOperation = repositoryConnection.prepareUpdate(QueryLanguage.SPARQL, strInsert);
	    updateOperation.execute();
	    
	    try {
	      repositoryConnection.commit();
	    }
	    catch (Exception e) {
	      if (repositoryConnection.isActive())
	        repositoryConnection.rollback();
	    }
	}

	/*private static void query(RepositoryConnection repositoryConnection) {
	    TupleQuery tupleQuery = repositoryConnection.prepareTupleQuery(QueryLanguage.SPARQL, strQuery);
	    TupleQueryResult result = null;
	    try {
	    	result = tupleQuery.evaluate();
	    	while (result.hasNext()) {
		        BindingSet bindingSet = result.next();
		
		        SimpleLiteral name = (SimpleLiteral)bindingSet.getValue("name");
		        logger.trace("name = " + name.stringValue());
	      	}
	    }
	    catch (QueryEvaluationException qee) {
	    	logger.error(WTF_MARKER, qee.getStackTrace().toString(), qee);
	    }
	    finally {
	    	result.close();
	    }    
	}
	*/
  
	public static void main(String[] args) {
		//ArrayList<RelationOrganizationCountry> arrayRelationOC = new ArrayList<RelationOrganizationCountry>();
		//ArrayList<RelationPersonCountry> arrayRelationPC = new ArrayList<RelationPersonCountry>();
		//ArrayList<RelationOrganizationTime> arrayRelationOT = new ArrayList<RelationOrganizationTime>();
		//ArrayList<RelationEventLocation> arrayRelationEL = new ArrayList<RelationEventLocation>();
		ArrayList<RelationEventTime> arrayRelationET = new ArrayList<RelationEventTime>();
		ArrayList<entity.Country> arrayCountries = new ArrayList<entity.Country>();
		//ArrayList<entity.Event> arrayEvents = new ArrayList<entity.Event>();
		//ArrayList<entity.Event> arrayEventsDuplicate = new ArrayList<entity.Event>();
		RepositoryConnection repositoryConnection = null;
		try {
		repositoryConnection = getRepositoryConnection();
		/*
		arrayCountries = CountryGenerator.generateCountries(100, 1);
		strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < 100; i++) {
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getDescription()) + "> .";
		}
		strInsert+= "\n}";
		insert(repositoryConnection);
		*/
		/*
		arrayEvents = EventGenerator.generateEvents(100, 1);
		for (int j = 0; j < 100; j++) {		
			for (int k = 0; k < 1000; k++) {
				String ID = Integer.toString(k + 1);
				String newname = arrayEvents.get(j).getName() + "_" + ID;
				entity.Event a = new entity.Event(arrayEvents.get(j).getName(), newname, arrayEvents.get(j).getDescription(), arrayEvents.get(j).getLink(), arrayEvents.get(j).getDate());
				arrayEventsDuplicate.add(k, a);
			}
			strInsert = "";
			strInsert += "INSERT DATA {";
			for (int i = 0; i < 1000; i++) {
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getName()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getDescription()) + "> .";
			}
			strInsert+= "\n}";
			insert(repositoryConnection);
		}
		*/
		
		for (int a = 1; a <= 1; a += 10) {
			for (int b = 1; b <= 1; b += 10) {
				for (int c = 1; c <= 100; c++) {
					arrayRelationET = RelationEventTime.createRelationET(100, a, 100, 1, b);
					strInsert = "";
					strInsert += "INSERT DATA {";
					for (int i = 0; i < 10000; i++) {
						strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationET.get(i).event.getLabel()) + "> <http://xmlns.com/foaf/0.1/" + SpaceToUnderscoreConverter.convert(arrayRelationET.get(i).getRelation()) + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationET.get(i).time.getLabel()) + "> .";
					}
					strInsert+= "\n}";
					insert(repositoryConnection); 
				}
			}
		}
		
		/*
		for (int a = 1; a <= 1; a += 10) {
			for (int b = 1; b <= 1; b += 10) {
				for (int c = 1; c <= 1; c += 10) {
					arrayRelationEL = RelationEventLocation.createRelationEL(100, a, 10, b, 1, c);
					strInsert = "";
					strInsert += "INSERT DATA {";
					for (int i = 0; i < 1000; i++) {
						strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationEL.get(i).event.getLabel()) + "> <http://xmlns.com/foaf/0.1/" + SpaceToUnderscoreConverter.convert(arrayRelationEL.get(i).getRelation()) + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationEL.get(i).location.getLabel()) + "> .";
					}
					strInsert+= "\n}";
					insert(repositoryConnection); 
				}
			}
		}
		*/
		}
		catch (Throwable t) {
		  logger.error(WTF_MARKER, t.getMessage(), t);
		} 
		finally {
		  repositoryConnection.close();
		}
		/*strQuery += 
				"SELECT ?name FROM DEFAULT WHERE {" +
				"?s <http://xmlns.com/foaf/0.1/name> ?name .}";
			*/	
		/*
		RepositoryConnection repositoryConnection = null;
	    try {   
	      repositoryConnection = getRepositoryConnection();
	      
	      insert(repositoryConnection);
	      //query(repositoryConnection);      
	      
	    } 
	    catch (Throwable t) {
	      logger.error(WTF_MARKER, t.getMessage(), t);
	    } 
	    finally {
	      repositoryConnection.close();
	    }
	    */
	}  
}