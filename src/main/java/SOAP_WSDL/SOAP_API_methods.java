package SOAP_WSDL;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.DeleteResult;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.Error;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

import REST_HTTP.API_Variables;

public class SOAP_API_methods implements API_Variables
{
	static EnterpriseConnection connection;
	static String ContactID;
	
	public static void getConnection()
	{
		ConnectorConfig config = new ConnectorConfig();
		config.setUsername(USERNAME);
		config.setPassword(PASSWORD);

		try {
			connection = Connector.newConnection(config);

			// display some current settings
			System.out.println("Auth EndPoint: "+config.getAuthEndpoint());
			System.out.println("Service EndPoint: "+config.getServiceEndpoint());
			System.out.println("Username: "+config.getUsername());
			System.out.println("SessionId: "+config.getSessionId());
		}
		catch(ConnectionException e1) {
			e1.printStackTrace();
		} 
	}
	
	public static void closeConnection()
	{
		try {
			connection.logout();
			System.out.println("Logged out.");
		} catch (ConnectionException ce) {
			ce.printStackTrace();
		}
	}

	public static void queryContacts() 
	{
		System.out.println("Querying for Contact ID...");
		try {
			QueryResult queryResults = connection.query(query);
			if (queryResults.getSize() == 1) 
			{
				Contact ContID = (Contact) queryResults.getRecords()[0];
				ContactID = ContID.getId();
				System.out.println("ContactID: "+ContactID);
			}
			else
			{
				System.out.println("No ID returned or more than one ID returned");
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}    
	}

	public static void createContacts() 
	{
		System.out.println("Creating Contacts...");
		Contact[] records = new Contact[1];
		try {
			Contact contact = new Contact();
			contact.setFirstName(ContactFirstName);
			contact.setLastName(ContactLastNameSOAP);
			contact.setTitle(USERNAME);
			contact.setEmail(ContactEmail);
			records[0] = contact;
			SaveResult[] saveResults = connection.create(records);
			if (saveResults[0].isSuccess()) 
			{
				ContactID = saveResults[0].getId();
				System.out.println("Successfully created record - Id: " +ContactID );
			} else 
			{
				Error[] errors = saveResults[0].getErrors();
				System.out.println("ERROR creating record: " + errors[0].getMessage()); 
			} 
		}
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
	} 

	public static void updateContacts() 
	{ 
		System.out.println("Updating Contacts..."); 
		Contact[] records = new Contact[1]; 
		try 
		{ 
			QueryResult queryResults = connection.query(query); 
			if (queryResults.getSize() == 1) 
			{
				Contact cont = (Contact)queryResults.getRecords()[0];
				System.out.println("Updating Id: " + cont.getId());
				// modify the name of the Contact
				cont.setLastName(cont.getLastName()+" -- UPDATED");
				cont.setMobilePhone("456852951357");
				cont.setPhone("7896541230");
				records[0] = cont;
			}
			SaveResult[] saveResults = connection.update(records);

			if (saveResults[0].isSuccess()) 
			{
				System.out.println("Successfully updated record - Id: " + saveResults[0].getId());
			} else 
			{
				Error[] errors = saveResults[0].getErrors();
				System.out.println("ERROR updating record: " + errors[0].getMessage()); 
			}  
		} 
		catch (Exception e) 
		{ e.printStackTrace();
		} 
	} 

	public static void deleteContacts() 
	{ 
		System.out.println("Deleting Contacts..."); 
		String[] ids = new String[1]; 
		try 
		{ 
			QueryResult queryResults = connection.query(query); 
			if (queryResults.getSize() == 1) 
			{
				Contact cont = (Contact)queryResults.getRecords()[0];
				ids[0] = cont.getId();
				System.out.println("Deleting Id: " + cont.getId());
			}
			DeleteResult[] deleteResults = connection.delete(ids);

			if (deleteResults[0].isSuccess()) 
			{
				System.out.println("Successfully deleted record - Id: " + deleteResults[0].getId());
			} else 
			{
				Error[] errors = deleteResults[0].getErrors();
				System.out.println("ERROR deleting record: " + errors[0].getMessage());

			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}    

	}

}
