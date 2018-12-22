package GraphDB.OOP;

import java.util.ArrayList;

import org.eclipse.rdf4j.query.QueryLanguage;
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
import createdata.RelationCountryEvent;
import createdata.RelationEventLocation;
import createdata.RelationEventTime;
import createdata.RelationOrganizationCountry;
import createdata.RelationOrganizationTime;
import createdata.RelationPersonCountry;
import createdata.RelationPersonEvent;
import createdata.RelationPersonLocation;
import createdata.SpaceToUnderscoreConverter;

public class InsertDataToGraphDB {
	private static Logger logger = LoggerFactory.getLogger(InsertDataToGraphDB.class);
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
		//strQuery += 
		//"SELECT ?name FROM DEFAULT WHERE {" +
        //"<http://dbpedia.org/resource/Shinjo_Abe> " + "<http://xmlns.com/foaf/0.1/name> ?name .}";
		
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
	/*
	private static void query(RepositoryConnection repositoryConnection) {
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
		ArrayList<RelationOrganizationCountry> arrayRelationOC = new ArrayList<RelationOrganizationCountry>();
		ArrayList<RelationPersonCountry> arrayRelationPC = new ArrayList<RelationPersonCountry>();
		ArrayList<RelationOrganizationTime> arrayRelationOT = new ArrayList<RelationOrganizationTime>();
		ArrayList<RelationEventLocation> arrayRelationEL = new ArrayList<RelationEventLocation>();
		ArrayList<RelationEventTime> arrayRelationET = new ArrayList<RelationEventTime>();
		ArrayList<RelationPersonEvent> arrayRelationPE = new ArrayList<RelationPersonEvent>();
		ArrayList<RelationPersonLocation> arrayRelationPL = new ArrayList<RelationPersonLocation>();
		ArrayList<RelationCountryEvent> arrayRelationCE = new ArrayList<RelationCountryEvent>();
		ArrayList<entity.Country> arrayCountries = new ArrayList<entity.Country>();
		ArrayList<entity.Event> arrayEvents = new ArrayList<entity.Event>();
		ArrayList<entity.Person> arrayPeople = new ArrayList<entity.Person>();
		ArrayList<entity.Organization> arrayOrganizations = new ArrayList<entity.Organization>();
		ArrayList<entity.Location> arrayLocations = new ArrayList<entity.Location>();
		ArrayList<entity.Event> arrayEventsDuplicate = new ArrayList<entity.Event>();
		ArrayList<entity.Person> arrayPeopleDuplicate = new ArrayList<entity.Person>();
		ArrayList<entity.Organization> arrayOrganizationsDuplicate = new ArrayList<entity.Organization>();
		ArrayList<entity.Location> arrayLocationsDuplicate = new ArrayList<entity.Location>();
		ArrayList<entity.Country> arrayCountriesDuplicate = new ArrayList<entity.Country>();
		arrayOrganizations = OrganizationGenerator.generateOrganizations(100, 1);// 1 means read from the 1st line in the file that contains data about organization
		arrayCountries = CountryGenerator.generateCountries(100, 1);			 // 100 means read 100 lines from the 1st line
		arrayPeople = PeopleGenerator.generatePeople(50, 1);
		arrayLocations = LocationGenerator.generateLocations(10, 1);
		arrayEvents = EventGenerator.generateEvents(100, 1);
		RepositoryConnection repositoryConnection = null;
		try {
		repositoryConnection = getRepositoryConnection();
		
		//create original entities
		/*
		strInsert = "";															 
		strInsert += "INSERT DATA {";
		for (int i = 0; i < 100; i++) {
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizations.get(i).getDate()) + "> .";
		}
		strInsert+= "\n}";
		insert(repositoryConnection);
		
		strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < 100; i++) {
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountries.get(i).getDate()) + "> .";
		}
		strInsert+= "\n}";
		insert(repositoryConnection);
		
		strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < 50; i++) {
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeople.get(i).getDate()) + "> .";
		}
		strInsert+= "\n}";
		insert(repositoryConnection);
		
		strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < 10; i++) {
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocations.get(i).getDate()) + "> .";
		}
		strInsert+= "\n}";
		insert(repositoryConnection);
		
		strInsert = "";
		strInsert += "INSERT DATA {";
		for (int i = 0; i < 100; i++) {
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getName()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getDescription()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getLink()) + "> .";
			strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEvents.get(i).getDate()) + "> .";
		}
		strInsert+= "\n}";
		insert(repositoryConnection);
		*/
		
		//create additional entities to enlarge the scale of database, based on original entities
		
		//create additional events
		/*
		for (int j = 0; j < 100; j++) {// j ranges from 0 to the amount of original events created - 1
			for (int k = 0; k < 5000; k++) {// k = numbers of different entities, created from 1 original entity, with different labels
				String ID = Integer.toString(k + 1);// ID adds to original label to create new entity
				String newname = arrayEvents.get(j).getName() + "_" + ID;// new label
				entity.Event a = new entity.Event(arrayEvents.get(j).getName(), newname, arrayEvents.get(j).getDescription(), arrayEvents.get(j).getLink(), arrayEvents.get(j).getDate());
				arrayEventsDuplicate.add(k, a);
			}
			strInsert = "";
			strInsert += "INSERT DATA {";
			for (int i = 0; i < 5000; i++) {
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getName()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getDescription()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getLink()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayEventsDuplicate.get(i).getDate()) + "> .";
			}
			strInsert+= "\n}";
			insert(repositoryConnection);
		}
		*/
		//create additional countries
		/*
		for (int j = 0; j < 100; j++) {// j ranges from 0 to the amount of original countries created - 1
			for (int k = 0; k < 2500; k++) {// k = numbers of different entities, created from 1 original entity, with different labels
				String ID = Integer.toString(k + 1);// ID adds to original label to create new entity
				String newname = arrayCountries.get(j).getName() + "_" + ID;// new label
				entity.Country a = new entity.Country(arrayCountries.get(j).getName(), newname, arrayCountries.get(j).getDescription(), arrayCountries.get(j).getLink(), arrayCountries.get(j).getDate());
				arrayCountriesDuplicate.add(k, a);
			}
			strInsert = "";
			strInsert += "INSERT DATA {";
			for (int i = 0; i < 2500; i++) {
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getName()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getDescription()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getLink()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayCountriesDuplicate.get(i).getDate()) + "> .";
			}
			strInsert+= "\n}";
			insert(repositoryConnection);
		}
		*/
		//create additional people
		/*
		for (int j = 0; j < 50; j++) {// j ranges from 0 to the amount of original people created - 1
			for (int k = 0; k < 2500; k++) {// k = numbers of different entities, created from 1 original entity, with different labels
				String ID = Integer.toString(k + 1);// ID adds to original label to create new entity
				String newname = arrayPeople.get(j).getName() + "_" + ID;// new label
				entity.Person a = new entity.Person(arrayPeople.get(j).getName(), newname, arrayPeople.get(j).getDescription(), arrayPeople.get(j).getLink(), arrayPeople.get(j).getDate(), arrayPeople.get(j).getCareer());
				arrayPeopleDuplicate.add(k, a);
			}
			strInsert = "";
			strInsert += "INSERT DATA {";
			for (int i = 0; i < 2500; i++) {
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getName()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getDescription()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getLink()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayPeopleDuplicate.get(i).getDate()) + "> .";
			}
			strInsert+= "\n}";
			insert(repositoryConnection);
		}
		*/
		//create additional organizations
		/*
		for (int j = 0; j < 100; j++) {// j ranges from 0 to the amount of original organization created - 1
			for (int k = 0; k < 2500; k++) {// k = numbers of different entities, created from 1 original entity, with different labels
				String ID = Integer.toString(k + 1);// ID adds to original label to create new entity
				String newname = arrayOrganizations.get(j).getName() + "_" + ID;// new label
				entity.Organization a = new entity.Organization(arrayOrganizations.get(j).getName(), newname, arrayOrganizations.get(j).getDescription(), arrayOrganizations.get(j).getLink(), arrayOrganizations.get(j).getDate());
				arrayOrganizationsDuplicate.add(k, a);
			}
			strInsert = "";
			strInsert += "INSERT DATA {";
			for (int i = 0; i < 2500; i++) {
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getName()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getDescription()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getLink()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayOrganizationsDuplicate.get(i).getDate()) + "> .";
			}
			strInsert+= "\n}";
			insert(repositoryConnection);
		}
		*/
		//create additional locations
		/*
		for (int j = 0; j < 10; j++) {// j ranges from 0 to the amount of original locations created - 1
			for (int k = 0; k < 2500; k++) {// k = numbers of different entities, created from 1 original entity, with different labels
				String ID = Integer.toString(k + 1);// ID adds to original label to create new entity
				String newname = arrayLocations.get(j).getName() + "_" + ID;// new label
				entity.Location a = new entity.Location(arrayLocations.get(j).getName(), newname, arrayLocations.get(j).getDescription(), arrayLocations.get(j).getLink(), arrayLocations.get(j).getDate());
				arrayLocationsDuplicate.add(k, a);
			}
			strInsert = "";
			strInsert += "INSERT DATA {";
			for (int i = 0; i < 2500; i++) {
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocationsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasName" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocationsDuplicate.get(i).getName()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocationsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDescription" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocationsDuplicate.get(i).getDescription()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocationsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasLink" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocationsDuplicate.get(i).getLink()) + "> .";
				strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocationsDuplicate.get(i).getLabel()) + "> <http://xmlns.com/foaf/0.1/" + "hasDate" + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayLocationsDuplicate.get(i).getDate()) + "> .";
			}
			strInsert+= "\n}";
			insert(repositoryConnection);
		}
		*/
		/*
		for (int startLineS = 1; startLineS <= 1; startLineS += 10) {// information about subjects will be read from line 1 and be read once
			for (int startLineP = 1; startLineP <= 1; startLineP += 10) {// information about predicates will be read from line 1 and be read once
				for (int queryCount = 1; queryCount <= 100; queryCount++) {// numbers of insert queries to be executed, exclusively for relation with Time participating as an object
					arrayRelationET = RelationEventTime.createRelationET(100, startLineS, 100, 1, startLineP);// (number of subjects, starting line of the file containing information about subjects to be read, number of Time entity to be created, number of predicates, starting line of the file containing information about predicates to be read)
					strInsert = "";
					strInsert += "INSERT DATA {";
					for (int i = 0; i < 10000; i++) {// i will stop at (number of subjects*number of Time entity served as objects*number of predicates), which is the number of relations created
						strInsert += "\n<http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationET.get(i).event.getLabel()) + "> <http://xmlns.com/foaf/0.1/" + SpaceToUnderscoreConverter.convert(arrayRelationET.get(i).getRelation()) + "> <http://dbpedia.org/resource/" + SpaceToUnderscoreConverter.convert(arrayRelationET.get(i).time.getLabel()) + "> .";
					}
					strInsert+= "\n}";
					insert(repositoryConnection); 
				}
			}
		}
		*/
		
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
		
		}
		catch (Throwable t) {
		  logger.error(WTF_MARKER, t.getMessage(), t);
		} 
		finally {
		  repositoryConnection.close();
		}
		//strQuery += 
		//		"SELECT ?name WHERE {" +
		//		"<http://dbpedia.org/resource/Viet_Nam>" + "<http://xmlns.com/foaf/0.1/hasName> ?name .}";	
		
		//RepositoryConnection repositoryConnection = null;
		/*
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