package add.data.to.graphdb;

import org.eclipse.rdf4j.model.Value;
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

import create.entity.CountryGenerator;
import create.entity.EventGenerator;
import create.entity.LocationGenerator;
import create.entity.OrganizationGenerator;
import create.entity.PeopleGenerator;
import create.query.insert.InsertAdditionalCountriesQueries;
import create.query.insert.InsertAdditionalEventsQueries;
import create.query.insert.InsertAdditionalLocationsQueries;
import create.query.insert.InsertAdditionalOrganizationsQueries;
import create.query.insert.InsertAdditionalPeopleQueries;
import create.query.insert.InsertAdditionalTimeQueries;
import create.query.insert.InsertOriginalEntitiesQueries;
import create.query.insert.InsertRelationCE;
import create.query.insert.InsertRelationEL;
import create.query.insert.InsertRelationET;
import create.query.insert.InsertRelationOC;
import create.query.insert.InsertRelationOT;
import create.query.insert.InsertRelationPC;
import create.query.insert.InsertRelationPE;
import create.query.insert.InsertRelationPL;
import create.relation.RelationCountryEvent;
import create.relation.RelationEventLocation;
import create.relation.RelationEventTime;
import create.relation.RelationOrganizationCountry;
import create.relation.RelationOrganizationTime;
import create.relation.RelationPersonCountry;
import create.relation.RelationPersonEvent;
import create.relation.RelationPersonLocation;

public class InsertDataToGraphDB {
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

	public static RepositoryConnection getRepositoryConnection() {
		Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
		repository.initialize();
		RepositoryConnection repositoryConnection = repository.getConnection();
		return repositoryConnection;
	}

	public static void insert(RepositoryConnection repositoryConnection, String strInsert) {
		repositoryConnection.begin();

		Update updateOperation = repositoryConnection.prepareUpdate(QueryLanguage.SPARQL, strInsert);
		updateOperation.execute();

		try {
			repositoryConnection.commit();
		} catch (Exception e) {
			if (repositoryConnection.isActive())
				repositoryConnection.rollback();
		}
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

	public static void insertOriginalEntities(RepositoryConnection repositoryConnection) {
		try {
			repositoryConnection = getRepositoryConnection();
			String strInsert = InsertOriginalEntitiesQueries.createQuery();
			insert(repositoryConnection, strInsert);
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}

	public static void insertAdditionalEvents(RepositoryConnection repositoryConnection) {
		try {
			repositoryConnection = getRepositoryConnection();
			for (int i = 0; i < InsertAdditionalEventsQueries.getCount(); i++) {
				String strInsert = InsertAdditionalEventsQueries.createQuery(i);
				insert(repositoryConnection, strInsert);
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}

	public static void insertAdditionalCountries(RepositoryConnection repositoryConnection) {
		try {
			repositoryConnection = getRepositoryConnection();
			for (int i = 0; i < InsertAdditionalCountriesQueries.getCount(); i++) {
				String strInsert = InsertAdditionalCountriesQueries.createQuery(i);
				insert(repositoryConnection, strInsert);
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}

	public static void insertAdditionalPeople(RepositoryConnection repositoryConnection) {
		try {
			repositoryConnection = getRepositoryConnection();
			for (int i = 0; i < InsertAdditionalPeopleQueries.getCount(); i++) {
				String strInsert = InsertAdditionalPeopleQueries.createQuery(i);
				insert(repositoryConnection, strInsert);
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}

	public static void insertAdditionalOrganizations(RepositoryConnection repositoryConnection) {
		try {
			repositoryConnection = getRepositoryConnection();
			for (int i = 0; i < InsertAdditionalOrganizationsQueries.getCount(); i++) {
				String strInsert = InsertAdditionalOrganizationsQueries.createQuery(i);
				insert(repositoryConnection, strInsert);
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}

	public static void insertAdditionalLocations(RepositoryConnection repositoryConnection) {
		try {
			repositoryConnection = getRepositoryConnection();
			for (int i = 0; i < InsertAdditionalLocationsQueries.getCount(); i++) {
				String strInsert = InsertAdditionalLocationsQueries.createQuery(i);
				insert(repositoryConnection, strInsert);
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}
	
	public static void insertAdditionalTime(RepositoryConnection repositoryConnection, int count) {
		try {
			int limit = count/10000;
			repositoryConnection = getRepositoryConnection();
			for (int i = 0; i < limit; i++) {
				String strInsert = InsertAdditionalTimeQueries.createQuery(i, limit);
				insert(repositoryConnection, strInsert);
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}
	
	public static void insertEntityAsRequest(RepositoryConnection repositoryConnection, int count) {
		try {
			if (count < 5000000) {
				System.out.println("Please insert a number greater that or equal 5000000");
				return;
			}
			if (count % 1000000 != 0) {
				System.out.println("Please insert a number which is a multiple of 1000000");
			}
			int limit = (count - 3500000)/10000 ;
			repositoryConnection = getRepositoryConnection();
			
			for (int i = 0; i < limit; i++) {
				String strInsert = InsertAdditionalTimeQueries.createQuery(i, limit);
				insert(repositoryConnection, strInsert);
			}
			for (int i = 0; i < InsertAdditionalLocationsQueries.getCount(); i++) {
				String strInsert = InsertAdditionalLocationsQueries.createQuery(i);
				insert(repositoryConnection, strInsert);
			}
			for (int i = 0; i < InsertAdditionalOrganizationsQueries.getCount(); i++) {
				String strInsert = InsertAdditionalOrganizationsQueries.createQuery(i);
				insert(repositoryConnection, strInsert);
			}
			for (int i = 0; i < InsertAdditionalPeopleQueries.getCount(); i++) {
				String strInsert = InsertAdditionalPeopleQueries.createQuery(i);
				insert(repositoryConnection, strInsert);
			}
			for (int i = 0; i < InsertAdditionalCountriesQueries.getCount(); i++) {
				String strInsert = InsertAdditionalCountriesQueries.createQuery(i);
				insert(repositoryConnection, strInsert);
			}
			for (int i = 0; i < InsertAdditionalEventsQueries.getCount(); i++) {
				String strInsert = InsertAdditionalEventsQueries.createQuery(i);
				insert(repositoryConnection, strInsert);
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}
	
	public static void insertRelationET(RepositoryConnection repositoryConnection, int relationCount) {
		if (relationCount < 10000) {
			System.out.println("Please enter a number greater than 10000");
			return;
		}
		try {
			for (int startLineS = 1; startLineS <= EventGenerator.getCount() - RelationEventTime.getJumpS()
					+ 1; startLineS += RelationEventTime.getJumpS()) {
				for (int startLineP = 1; startLineP <= RelationEventTime.getCount() - RelationEventTime.getJumpP()
						+ 1; startLineP += RelationEventTime.getJumpP()) {
					for (int k = 1; k <= relationCount / (RelationEventTime.getJumpS() * RelationEventTime.getJumpP()
							* InsertRelationET.getTimeEntityCount()); k++) {
						String strInsert = InsertRelationET.createQuery(startLineS, startLineP);
						insert(repositoryConnection, strInsert);
					}
				}
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}

	public static void insertRelationOT(RepositoryConnection repositoryConnection, int relationCount) {
		if (relationCount < 10000) {
			System.out.println("Please enter a number greater than 10000");
			return;
		}
		try {
			for (int startLineS = 1; startLineS <= OrganizationGenerator.getCount()
					- RelationOrganizationTime.getJumpS() + 1; startLineS += RelationOrganizationTime.getJumpS()) {
				for (int startLineP = 1; startLineP <= RelationOrganizationTime.getCount()
						- RelationOrganizationTime.getJumpP() + 1; startLineP += RelationOrganizationTime.getJumpP()) {
					for (int k = 1; k <= relationCount / (RelationOrganizationTime.getJumpS()
							* RelationOrganizationTime.getJumpP() * InsertRelationOT.getTimeEntityCount()); k++) {
						String strInsert = InsertRelationOT.createQuery(startLineS, startLineP);
						insert(repositoryConnection, strInsert);
					}
				}
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}

	public static void insertRelationCE(RepositoryConnection repositoryConnection) {
		try {
			for (int startLineS = 1; startLineS <= CountryGenerator.getCount() - RelationCountryEvent.getJumpS()
					+ 1; startLineS += RelationCountryEvent.getJumpS()) {
				for (int startLineO = 1; startLineO <= EventGenerator.getCount() - RelationCountryEvent.getJumpO()
						+ 1; startLineO += RelationCountryEvent.getJumpO()) {
					for (int startLineP = 1; startLineP <= RelationCountryEvent.getCount()
							- RelationCountryEvent.getJumpP() + 1; startLineP += RelationCountryEvent.getJumpP()) {
						String strInsert = InsertRelationCE.createQuery(startLineS, startLineP, startLineO);
						insert(repositoryConnection, strInsert);
					}
				}
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}

	public static void insertRelationEL(RepositoryConnection repositoryConnection) {
		try {
			for (int startLineS = 1; startLineS <= EventGenerator.getCount() - RelationEventLocation.getJumpS()
					+ 1; startLineS += RelationEventLocation.getJumpS()) {
				for (int startLineO = 1; startLineO <= LocationGenerator.getCount() - RelationEventLocation.getJumpO()
						+ 1; startLineO += RelationEventLocation.getJumpO()) {
					for (int startLineP = 1; startLineP <= RelationEventLocation.getCount()
							- RelationEventLocation.getJumpP() + 1; startLineP += RelationEventLocation.getJumpP()) {
						String strInsert = InsertRelationEL.createQuery(startLineS, startLineP, startLineO);
						insert(repositoryConnection, strInsert);
					}
				}
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}

	public static void insertRelationOC(RepositoryConnection repositoryConnection) {
		try {
			for (int startLineS = 1; startLineS <= OrganizationGenerator.getCount()
					- RelationOrganizationCountry.getJumpS()
					+ 1; startLineS += RelationOrganizationCountry.getJumpS()) {
				for (int startLineO = 1; startLineO <= CountryGenerator.getCount()
						- RelationOrganizationCountry.getJumpO()
						+ 1; startLineO += RelationOrganizationCountry.getJumpO()) {
					for (int startLineP = 1; startLineP <= RelationOrganizationCountry.getCount()
							- RelationOrganizationCountry.getJumpP()
							+ 1; startLineP += RelationOrganizationCountry.getJumpP()) {
						String strInsert = InsertRelationOC.createQuery(startLineS, startLineP, startLineO);
						insert(repositoryConnection, strInsert);
					}
				}
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}

	public static void insertRelationPC(RepositoryConnection repositoryConnection) {
		try {
			for (int startLineS = 1; startLineS <= PeopleGenerator.getCount() - RelationPersonCountry.getJumpS()
					+ 1; startLineS += RelationPersonCountry.getJumpS()) {
				for (int startLineO = 1; startLineO <= CountryGenerator.getCount() - RelationPersonCountry.getJumpO()
						+ 1; startLineO += RelationPersonCountry.getJumpO()) {
					for (int startLineP = 1; startLineP <= RelationPersonCountry.getCount()
							- RelationPersonCountry.getJumpP() + 1; startLineP += RelationPersonCountry.getJumpP()) {
						String strInsert = InsertRelationPC.createQuery(startLineS, startLineP, startLineO);
						insert(repositoryConnection, strInsert);
					}
				}
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}

	public static void insertRelationPE(RepositoryConnection repositoryConnection) {
		try {
			for (int startLineS = 1; startLineS <= PeopleGenerator.getCount() - RelationPersonEvent.getJumpS()
					+ 1; startLineS += RelationPersonEvent.getJumpS()) {
				for (int startLineO = 1; startLineO <= EventGenerator.getCount() - RelationPersonEvent.getJumpO()
						+ 1; startLineO += RelationPersonEvent.getJumpO()) {
					for (int startLineP = 1; startLineP <= RelationPersonEvent.getCount()
							- RelationPersonEvent.getJumpP() + 1; startLineP += RelationPersonEvent.getJumpP()) {
						String strInsert = InsertRelationPE.createQuery(startLineS, startLineP, startLineO);
						insert(repositoryConnection, strInsert);
					}
				}
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}

	public static void insertRelationPL(RepositoryConnection repositoryConnection) {
		try {
			for (int startLineS = 1; startLineS <= PeopleGenerator.getCount() - RelationPersonLocation.getJumpS()
					+ 1; startLineS += RelationPersonLocation.getJumpS()) {
				for (int startLineO = 1; startLineO <= LocationGenerator.getCount() - RelationPersonLocation.getJumpO()
						+ 1; startLineO += RelationPersonLocation.getJumpO()) {
					for (int startLineP = 1; startLineP <= RelationPersonLocation.getCount()
							- RelationPersonLocation.getJumpP() + 1; startLineP += RelationPersonLocation.getJumpP()) {
						String strInsert = InsertRelationPL.createQuery(startLineS, startLineP, startLineO);
						insert(repositoryConnection, strInsert);
					}
				}
			}
		} catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}
	
	public static void insertRelationAsRequest(RepositoryConnection repositoryConnection, int count) {
		try {
			if (count < 5000000) {
				System.out.println("Please insert a number greater that or equal 5000000");
				return;
			}
			if (count % 1000000 != 0) {
				System.out.println("Please insert a number which is a multiple of 1000000");
			}
			int limit = count - 3000000;
			repositoryConnection = getRepositoryConnection();
			for (int startLineS = 1; startLineS <= OrganizationGenerator.getCount()
					- RelationOrganizationTime.getJumpS() + 1; startLineS += RelationOrganizationTime.getJumpS()) {
				for (int startLineP = 1; startLineP <= RelationOrganizationTime.getCount()
						- RelationOrganizationTime.getJumpP() + 1; startLineP += RelationOrganizationTime.getJumpP()) {
					for (int k = 1; k <= limit / (RelationOrganizationTime.getJumpS()
							* RelationOrganizationTime.getJumpP() * InsertRelationOT.getTimeEntityCount()); k++) {
						String strInsert = InsertRelationOT.createQuery(startLineS, startLineP);
						insert(repositoryConnection, strInsert);
					}
				}
			}
			for (int startLineS = 1; startLineS <= EventGenerator.getCount() - RelationEventTime.getJumpS()
					+ 1; startLineS += RelationEventTime.getJumpS()) {
				for (int startLineP = 1; startLineP <= RelationEventTime.getCount() - RelationEventTime.getJumpP()
						+ 1; startLineP += RelationEventTime.getJumpP()) {
					for (int k = 1; k <= 2000000 / (RelationEventTime.getJumpS() * RelationEventTime.getJumpP()
							* InsertRelationET.getTimeEntityCount()); k++) {
						String strInsert = InsertRelationET.createQuery(startLineS, startLineP);
						insert(repositoryConnection, strInsert);
					}
				}
			}
			for (int startLineS = 1; startLineS <= CountryGenerator.getCount() - RelationCountryEvent.getJumpS()
					+ 1; startLineS += RelationCountryEvent.getJumpS()) {
				for (int startLineO = 1; startLineO <= EventGenerator.getCount() - RelationCountryEvent.getJumpO()
						+ 1; startLineO += RelationCountryEvent.getJumpO()) {
					for (int startLineP = 1; startLineP <= RelationCountryEvent.getCount()
							- RelationCountryEvent.getJumpP() + 1; startLineP += RelationCountryEvent.getJumpP()) {
						String strInsert = InsertRelationCE.createQuery(startLineS, startLineP, startLineO);
						insert(repositoryConnection, strInsert);
					}
				}
			}
			for (int startLineS = 1; startLineS <= EventGenerator.getCount() - RelationEventLocation.getJumpS()
					+ 1; startLineS += RelationEventLocation.getJumpS()) {
				for (int startLineO = 1; startLineO <= LocationGenerator.getCount() - RelationEventLocation.getJumpO()
						+ 1; startLineO += RelationEventLocation.getJumpO()) {
					for (int startLineP = 1; startLineP <= RelationEventLocation.getCount()
							- RelationEventLocation.getJumpP() + 1; startLineP += RelationEventLocation.getJumpP()) {
						String strInsert = InsertRelationEL.createQuery(startLineS, startLineP, startLineO);
						insert(repositoryConnection, strInsert);
					}
				}
			}
			for (int startLineS = 1; startLineS <= OrganizationGenerator.getCount()
					- RelationOrganizationCountry.getJumpS()
					+ 1; startLineS += RelationOrganizationCountry.getJumpS()) {
				for (int startLineO = 1; startLineO <= CountryGenerator.getCount()
						- RelationOrganizationCountry.getJumpO()
						+ 1; startLineO += RelationOrganizationCountry.getJumpO()) {
					for (int startLineP = 1; startLineP <= RelationOrganizationCountry.getCount()
							- RelationOrganizationCountry.getJumpP()
							+ 1; startLineP += RelationOrganizationCountry.getJumpP()) {
						String strInsert = InsertRelationOC.createQuery(startLineS, startLineP, startLineO);
						insert(repositoryConnection, strInsert);
					}
				}
			}
			for (int startLineS = 1; startLineS <= PeopleGenerator.getCount() - RelationPersonCountry.getJumpS()
					+ 1; startLineS += RelationPersonCountry.getJumpS()) {
				for (int startLineO = 1; startLineO <= CountryGenerator.getCount() - RelationPersonCountry.getJumpO()
						+ 1; startLineO += RelationPersonCountry.getJumpO()) {
					for (int startLineP = 1; startLineP <= RelationPersonCountry.getCount()
							- RelationPersonCountry.getJumpP() + 1; startLineP += RelationPersonCountry.getJumpP()) {
						String strInsert = InsertRelationPC.createQuery(startLineS, startLineP, startLineO);
						insert(repositoryConnection, strInsert);
					}
				}
			}
			for (int startLineS = 1; startLineS <= PeopleGenerator.getCount() - RelationPersonEvent.getJumpS()
					+ 1; startLineS += RelationPersonEvent.getJumpS()) {
				for (int startLineO = 1; startLineO <= EventGenerator.getCount() - RelationPersonEvent.getJumpO()
						+ 1; startLineO += RelationPersonEvent.getJumpO()) {
					for (int startLineP = 1; startLineP <= RelationPersonEvent.getCount()
							- RelationPersonEvent.getJumpP() + 1; startLineP += RelationPersonEvent.getJumpP()) {
						String strInsert = InsertRelationPE.createQuery(startLineS, startLineP, startLineO);
						insert(repositoryConnection, strInsert);
					}
				}
			}
			for (int startLineS = 1; startLineS <= PeopleGenerator.getCount() - RelationPersonLocation.getJumpS()
					+ 1; startLineS += RelationPersonLocation.getJumpS()) {
				for (int startLineO = 1; startLineO <= LocationGenerator.getCount() - RelationPersonLocation.getJumpO()
						+ 1; startLineO += RelationPersonLocation.getJumpO()) {
					for (int startLineP = 1; startLineP <= RelationPersonLocation.getCount()
							- RelationPersonLocation.getJumpP() + 1; startLineP += RelationPersonLocation.getJumpP()) {
						String strInsert = InsertRelationPL.createQuery(startLineS, startLineP, startLineO);
						insert(repositoryConnection, strInsert);
					}
				}
			}
		}catch (Throwable t) {
			logger.error(WTF_MARKER, t.getMessage(), t);
		}
	}
}