package add.data.to.graphdb;

import org.eclipse.rdf4j.repository.RepositoryConnection;

public class Execution {
	public static void main(String[] args) {
		//setup
		RepositoryConnection repositoryConnection = null;
		InsertDataToGraphDB.setRepositiory("OOP");
		SelectDataFromGraphDB.setRepositiory("OOP");
		repositoryConnection = InsertDataToGraphDB.getRepositoryConnection();
		
		
		
		//create mass entities, only allows a number greater than or equal 5000000 and a multiple of 1000000
		InsertDataToGraphDB.insertEntityAsRequest(repositoryConnection, 5000000);
		
		//create mass relations, only allows a number greater than or equal 5000000 and a multiple of 1000000
		InsertDataToGraphDB.insertRelationAsRequest(repositoryConnection, 5000000);
		
		//query
		SelectDataFromGraphDB.queryAsRequest(repositoryConnection);
		repositoryConnection.close();
	}
}
