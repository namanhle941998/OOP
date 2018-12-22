package GraphDB.OOP;

import queries.*;
import createdata.*;
import java.util.ArrayList;

import org.eclipse.rdf4j.model.Value;
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

public class InsertDataToGraphDB {
	private static Logger logger = LoggerFactory.getLogger(InsertDataToGraphDB.class);

	private static final Marker WTF_MARKER = MarkerFactory.getMarker("WTF");
  
	private static final String GRAPHDB_SERVER = "http://localhost:7200/";
	private static final String REPOSITORY_ID = "Test1";

	
	private static String strInsert = "";
	private static String strQuery = "";
  
	private static RepositoryConnection getRepositoryConnection() {
	    Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
	    repository.initialize();
	    RepositoryConnection repositoryConnection = repository.getConnection();
	    return repositoryConnection;
	}
  
	public static void insert(RepositoryConnection repositoryConnection) {
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
	
	private static void query(RepositoryConnection repositoryConnection) {
	    TupleQuery tupleQuery = repositoryConnection.prepareTupleQuery(QueryLanguage.SPARQL, strQuery);
	    TupleQueryResult result = null;
	    try {
	    	result = tupleQuery.evaluate();
	    	while (result.hasNext()) {
		        BindingSet bindingSet = result.next();
		
		        Value name = bindingSet.getValue("name");
		        System.out.println(name);
	      	}
	    }
	    catch (QueryEvaluationException qee) {
	    	logger.error(WTF_MARKER, qee.getStackTrace().toString(), qee);
	    }
	    finally {
	    	result.close();
	    }    
	}
	
	public static void insertAdditionalEvents(RepositoryConnection repositoryConnection) {
		try {
			repositoryConnection = getRepositoryConnection();
			for (int i = 0; i < InsertAdditionalEventsQueries.getCount(); i++) {
				strInsert = InsertAdditionalEventsQueries.createQuery(strInsert, i);
				insert(repositoryConnection);
			}
		}
		catch (Throwable t) {
		  logger.error(WTF_MARKER, t.getMessage(), t);
		} 
	}
	
	public static void insertAdditionalCountries(RepositoryConnection repositoryConnection) {
		try {
			repositoryConnection = getRepositoryConnection();
			for (int i = 0; i < InsertAdditionalCountriesQueries.getCount(); i++) {
				strInsert = InsertAdditionalCountriesQueries.createQuery(strInsert, i);
				insert(repositoryConnection);
			}
		}
		catch (Throwable t) {
		  logger.error(WTF_MARKER, t.getMessage(), t);
		} 
	}
	
	public static void insertAdditionalPeople(RepositoryConnection repositoryConnection) {
		try {
			repositoryConnection = getRepositoryConnection();
			for (int i = 0; i < InsertAdditionalPeopleQueries.getCount(); i++) {
				strInsert = InsertAdditionalPeopleQueries.createQuery(strInsert, i);
				insert(repositoryConnection);
			}
		}
		catch (Throwable t) {
		  logger.error(WTF_MARKER, t.getMessage(), t);
		} 
	}
	
	public static void insertAdditionalOrganizations(RepositoryConnection repositoryConnection) {
		try {
			repositoryConnection = getRepositoryConnection();
			for (int i = 0; i < InsertAdditionalOrganizationsQueries.getCount(); i++) {
				strInsert = InsertAdditionalOrganizationsQueries.createQuery(strInsert, i);
				insert(repositoryConnection);
			}
		}
		catch (Throwable t) {
		  logger.error(WTF_MARKER, t.getMessage(), t);
		} 
	}
	
	public static void insertAdditionalLocations(RepositoryConnection repositoryConnection) {
		try {
			repositoryConnection = getRepositoryConnection();
			for (int i = 0; i < InsertAdditionalLocationsQueries.getCount(); i++) {
				strInsert = InsertAdditionalLocationsQueries.createQuery(strInsert, i);
				insert(repositoryConnection);
			}
		}
		catch (Throwable t) {
		  logger.error(WTF_MARKER, t.getMessage(), t);
		} 
	}
	
	public static void insertRelationET(RepositoryConnection repositoryConnection, int relationCount) {
		if (relationCount < 10000) {
			System.out.println("Please enter a number greater than 10000");
			return;
		}
		try {
			for (int startLineS = 1; startLineS <= EventGenerator.getCount() - RelationEventTime.getJumpS() + 1; startLineS += RelationEventTime.getJumpS()) {
				for (int startLineP = 1; startLineP <= RelationEventTime.getCount() - RelationEventTime.getJumpP() + 1; startLineP += RelationEventTime.getJumpP()) {
					for (int k = 1; k <= relationCount/(RelationEventTime.getJumpS() * RelationEventTime.getJumpP() * InsertRelationET.getTimeEntityCount()); k++) {
						strInsert = InsertRelationET.createQuery(strInsert, startLineS, startLineP);
						insert(repositoryConnection);
					}
				}
			}
		}
		catch (Throwable t) {
		  logger.error(WTF_MARKER, t.getMessage(), t);
		} 
	}
	
	public static void insertRelationOT(RepositoryConnection repositoryConnection, int relationCount) {
		if (relationCount < 10000) {
			System.out.println("Please enter a number greater than 10000");
			return;
		}
		try {
			for (int startLineS = 1; startLineS <= OrganizationGenerator.getCount() - RelationOrganizationTime.getJumpS() + 1; startLineS += RelationOrganizationTime.getJumpS()) {
				for (int startLineP = 1; startLineP <= RelationOrganizationTime.getCount() - RelationOrganizationTime.getJumpP() + 1; startLineP += RelationOrganizationTime.getJumpP()) {
					for (int k = 1; k <= relationCount/(RelationOrganizationTime.getJumpS() * RelationOrganizationTime.getJumpP() * InsertRelationOT.getTimeEntityCount()); k++) {
						strInsert = InsertRelationOT.createQuery(strInsert, startLineS, startLineP);
						insert(repositoryConnection);
					}
				}
			}
		}
		catch (Throwable t) {
		  logger.error(WTF_MARKER, t.getMessage(), t);
		} 
	}
	
	public static void main(String[] args) {
		ArrayList<RelationOrganizationCountry> arrayRelationOC = new ArrayList<RelationOrganizationCountry>();
		ArrayList<RelationPersonCountry> arrayRelationPC = new ArrayList<RelationPersonCountry>();
		ArrayList<RelationOrganizationTime> arrayRelationOT = new ArrayList<RelationOrganizationTime>();
		ArrayList<RelationEventLocation> arrayRelationEL = new ArrayList<RelationEventLocation>();
		ArrayList<RelationEventTime> arrayRelationET = new ArrayList<RelationEventTime>();
		ArrayList<RelationPersonEvent> arrayRelationPE = new ArrayList<RelationPersonEvent>();
		ArrayList<RelationPersonLocation> arrayRelationPL = new ArrayList<RelationPersonLocation>();
		ArrayList<RelationCountryEvent> arrayRelationCE = new ArrayList<RelationCountryEvent>();
		RepositoryConnection repositoryConnection = null;
		repositoryConnection = getRepositoryConnection();
		/*
		strQuery += 
			"SELECT ?name WHERE {" +
			"<http://dbpedia.org/resource/Viet_Nam>" + "<http://xmlns.com/foaf/0.1/hasName> ?name .}";
		query(repositoryConnection);
		*/
		//create original entities(fast)
		
		//strInsert = InsertOriginalEntitiesQueries.createQuery(strInsert);
		//insert(repositoryConnection);
		
		//create additional entities to enlarge the scale of database, based on original entities
		//create additional events(about 6-7 minutes)
		//insertAdditionalEvents(repositoryConnection);
		
		//create additional countries(about 6-7 minutes)
		//insertAdditionalCountries(repositoryConnection);
		
		//create additional people(about 3-4 minutes)
		//insertAdditionalPeople(repositoryConnection);
		
		//create additional organizations(about 6-7 minutes)
		//insertAdditionalOrganizations(repositoryConnection);
		
		//create additional locations(about 40-50 seconds)
		//insertAdditionalLocations(repositoryConnection);
		
		//create relations
		//create relations of event-time
		insertRelationET(repositoryConnection, 1000000);

		//create relations of organization-time
		insertRelationOT(repositoryConnection, 1000000);
		/*
		for (int startLineS = 1; startLineS <= 91; startLineS += 10) {// information about subjects will be read from line 1, be read 10 times and 10 subjects will be read in each loop
			for (int startLineP = 1; startLineP <= 91; startLineP += 10) {// information about predicates will be read from line 1, be read 10 times and 10 subjects will be read in each loop
				for (int queryCount = 1; queryCount <= 5; queryCount++) {// numbers of insert queries to be executed, exclusively for relation with Time participating as an object
					arrayRelationOT = RelationOrganizationTime.createRelationOT(10, startLineS, 100, 10, startLineP);// (number of subjects, starting line of the file containing information about subjects to be read, number of Time entity to be created, number of predicates, starting line of the file containing information about predicates to be read)
					strInsert = "";
					strInsert += "INSERT DATA {";
					for (int i = 0; i < 10000; i++) {// i will stop at (number of subjects*number of Time entity served as objects*number of predicates), which is the number of relations created
						strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationOT.get(i).organization.getLabel()) + "> <http://xmlns.com/foaf/0.1/" + SpaceToUnderscoreConverter.convert(arrayRelationOT.get(i).getRelation()) + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationOT.get(i).time.getLabel()) + "> .";
					}
					strInsert+= "\n}";
					insert(repositoryConnection); 
				}
			}
		}
		*/
		/*
		for (int startLineS = 1; startLineS <= 1; startLineS += 10) {// information about subjects will be read from line 1 and be read once
			for (int startLineO = 1; startLineO <= 1; startLineO += 10) {// information about objects will be read from line 1 and be read once
				for (int startLineP = 1; startLineP <= 1; startLineP += 10) {// information about predicate will be read from line 1 and be read once
					arrayRelationCE = RelationCountryEvent.createRelationCE(100, startLineS, 100, startLineO, 1, startLineP);// (number of subjects, starting line of the file containing information about subjects to be read, number of objects, starting line of the file containing information about objects to be read, number of predicates, starting line of the file containing information about predicates to be read)
					strInsert = "";
					strInsert += "INSERT DATA {";
					for (int i = 0; i < 10000; i++) {// i will stop at (number of subjects*number of objects*number of predicates), which is the number of relations created
						strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationCE.get(i).country.getLabel()) + "> <http://xmlns.com/foaf/0.1/" + SpaceToUnderscoreConverter.convert(arrayRelationCE.get(i).getRelation()) + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationCE.get(i).event.getLabel()) + "> .";
					}
					strInsert+= "\n}";
					insert(repositoryConnection); 
				}
			}
		}
		*/
		/*
		for (int startLineS = 1; startLineS <= 1; startLineS += 10) {// information about subjects will be read from line 1 and be read once
			for (int startLineO = 1; startLineO <= 1; startLineO += 10) {// information about objects will be read from line 1 and be read once
				for (int startLineP = 1; startLineP <= 1; startLineP += 10) {// information about predicate will be read from line 1 and be read once
					arrayRelationEL = RelationEventLocation.createRelationEL(100, startLineS, 10, startLineO, 1, startLineP);// (number of subjects, starting line of the file containing information about subjects to be read, number of objects, starting line of the file containing information about objects to be read, number of predicates, starting line of the file containing information about predicates to be read)
					strInsert = "";
					strInsert += "INSERT DATA {";
					for (int i = 0; i < 1000; i++) {// i will stop at (number of subjects*number of objects*number of predicates), which is the number of relations created
						strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationEL.get(i).event.getLabel()) + "> <http://xmlns.com/foaf/0.1/" + SpaceToUnderscoreConverter.convert(arrayRelationEL.get(i).getRelation()) + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationEL.get(i).location.getLabel()) + "> .";
					}
					strInsert+= "\n}";
					insert(repositoryConnection); 
				}
			}
		}
		*/
		/*
		for (int startLineS = 1; startLineS <= 91; startLineS += 10) {// information about subjects will be read from line 1, be read 10 times and 10 subjects will be read at each loop
			for (int startLineO = 1; startLineO <= 91; startLineO += 10) {// information about objects will be read from line 1, be read 10 times and 10 objects will be read at each loop
				for (int startLineP = 1; startLineP <= 1; startLineP += 10) {// information about predicate will be read from line 1 and be read once
					arrayRelationOC = RelationOrganizationCountry.createRelationOC(10, startLineS, 10, startLineO, 100, startLineP);// (number of subjects, starting line of the file containing information about subjects to be read, number of objects, starting line of the file containing information about objects to be read, number of predicates, starting line of the file containing information about predicates to be read)
					strInsert = "";
					strInsert += "INSERT DATA {";
					for (int i = 0; i < 10000; i++) {// i will stop at (number of subjects*number of objects*number of predicates), which is the number of relations created
						strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationOC.get(i).organization.getLabel()) + "> <http://xmlns.com/foaf/0.1/" + SpaceToUnderscoreConverter.convert(arrayRelationOC.get(i).getRelation()) + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationOC.get(i).country.getLabel()) + "> .";
					}
					strInsert+= "\n}";
					insert(repositoryConnection); 
				}
			}
		}
		*/
		/*
		for (int startLineS = 1; startLineS <= 1; startLineS += 10) {// information about subjects will be read from line 1 and be read once
			for (int startLineO = 1; startLineO <= 51; startLineO += 50) {// information about objects will be read from line 1, be read 2 times and 50 objects will be read at each loop
				for (int startLineP = 1; startLineP <= 1; startLineP += 10) {// information about predicate will be read from line 1 and be read once
					arrayRelationPC = RelationPersonCountry.createRelationPC(50, startLineS, 50, startLineO, 4, startLineP);// (number of subjects, starting line of the file containing information about subjects to be read, number of objects, starting line of the file containing information about objects to be read, number of predicates, starting line of the file containing information about predicates to be read)
					strInsert = "";
					strInsert += "INSERT DATA {";
					for (int i = 0; i < 10000; i++) {// i will stop at (number of subjects*number of objects*number of predicates), which is the number of relations created
						strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationPC.get(i).person.getLabel()) + "> <http://xmlns.com/foaf/0.1/" + SpaceToUnderscoreConverter.convert(arrayRelationPC.get(i).getRelation()) + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationPC.get(i).country.getLabel()) + "> .";
					}
					strInsert+= "\n}";
					insert(repositoryConnection); 
				}
			}
		}
		*/
		/*
		for (int startLineS = 1; startLineS <= 1; startLineS += 10) {// information about subjects will be read from line 1 and be read once
			for (int startLineO = 1; startLineO <= 1; startLineO += 10) {// information about objects will be read from line 1 and be read once
				for (int startLineP = 1; startLineP <= 1; startLineP += 10) {// information about predicate will be read from line 1 and be read once
					arrayRelationPE = RelationPersonEvent.createRelationPE(50, startLineS, 100, startLineO, 1, startLineP);// (number of subjects, starting line of the file containing information about subjects to be read, number of objects, starting line of the file containing information about objects to be read, number of predicates, starting line of the file containing information about predicates to be read)
					strInsert = "";
					strInsert += "INSERT DATA {";
					for (int i = 0; i < 5000; i++) {// i will stop at (number of subjects*number of objects*number of predicates), which is the number of relations created
						strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationPE.get(i).person.getLabel()) + "> <http://xmlns.com/foaf/0.1/" + SpaceToUnderscoreConverter.convert(arrayRelationPE.get(i).getRelation()) + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationPE.get(i).event.getLabel()) + "> .";
					}
					strInsert+= "\n}";
					insert(repositoryConnection); 
				}
			}
		}
		*/
		/*
		for (int startLineS = 1; startLineS <= 1; startLineS += 10) {// information about subjects will be read from line 1 and be read once
			for (int startLineO = 1; startLineO <= 1; startLineO += 10) {// information about objects will be read from line 1 and be read once
				for (int startLineP = 1; startLineP <= 1; startLineP += 10) {// information about predicate will be read from line 1 and be read once
					arrayRelationPL = RelationPersonLocation.createRelationPL(50, startLineS, 10, startLineO, 5, startLineP);// (number of subjects, starting line of the file containing information about subjects to be read, number of objects, starting line of the file containing information about objects to be read, number of predicates, starting line of the file containing information about predicates to be read)
					strInsert = "";
					strInsert += "INSERT DATA {";
					for (int i = 0; i < 2500; i++) {// i will stop at (number of subjects*number of objects*number of predicates), which is the number of relations created
						strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationPL.get(i).person.getLabel()) + "> <http://xmlns.com/foaf/0.1/" + SpaceToUnderscoreConverter.convert(arrayRelationPL.get(i).getRelation()) + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationPL.get(i).location.getLabel()) + "> .";
					}
					strInsert+= "\n}";
					insert(repositoryConnection); 
				}
			}
		}
		*/
		repositoryConnection.close();
	}  
}