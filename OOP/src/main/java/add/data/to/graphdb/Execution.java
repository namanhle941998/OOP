package add.data.to.graphdb;

import org.eclipse.rdf4j.repository.RepositoryConnection;

public class Execution {
	public static void main(String[] args) {
		RepositoryConnection repositoryConnection = null;
		repositoryConnection = InsertDataToGraphDB.getRepositoryConnection();
		
		//create original entities(fast)
		
		//InsertDataToGraphDB.insertOriginalEntities(repositoryConnection);
		
		//create additional entities to enlarge the scale of database, based on original entities
		//create additional events(about 6-7 minutes)
		//InsertDataToGraphDB.insertAdditionalEvents(repositoryConnection);
		
		//create additional countries(about 6-7 minutes)
		//InsertDataToGraphDB.insertAdditionalCountries(repositoryConnection);
		
		//create additional people(about 3-4 minutes)
		//InsertDataToGraphDB.insertAdditionalPeople(repositoryConnection);
		
		//create additional organizations(about 6-7 minutes)
		//InsertDataToGraphDB.insertAdditionalOrganizations(repositoryConnection);
		
		//create additional locations(about 40-50 seconds)
		//InsertDataToGraphDB.insertAdditionalLocations(repositoryConnection);
		
		//create relations
		//create relations of event-time
		//InsertDataToGraphDB.insertRelationET(repositoryConnection, 1000000);

		//create relations of organization-time
		//InsertDataToGraphDB.insertRelationOT(repositoryConnection, 1000000);
		
		//create relations of country-event
		//InsertDataToGraphDB.insertRelationCE(repositoryConnection);
		
		//create relations of event-location
		//InsertDataToGraphDB.insertRelationEL(repositoryConnection);

		//create relations of organization-country
		//InsertDataToGraphDB.insertRelationOC(repositoryConnection);
		
		//create relations of person-country
		//InsertDataToGraphDB.insertRelationPC(repositoryConnection);
		
		//create relations of person-event
		//InsertDataToGraphDB.insertRelationPE(repositoryConnection);
		
		//create relations of person-location
		//InsertDataToGraphDB.insertRelationPL(repositoryConnection);
		
		SelectDataFromGraphDB.queryAsRequest(repositoryConnection);
		repositoryConnection.close();
	}
}
