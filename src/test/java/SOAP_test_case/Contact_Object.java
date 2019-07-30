package SOAP_test_case;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.DeleteResult;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.Error;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import com.sun.jna.platform.win32.WinUser.INPUT;

import REST_HTTP.API_Contact_Inputs;
import REST_HTTP.API_Variables;
import SOAP_WSDL.SOAP_API_methods;

//public class Contact_Object implements API_Variables
public class Contact_Object extends API_Contact_Inputs
{
	//API_Contact_Inputs input = new API_Contact_Inputs();

	@BeforeTest
	public void getConnection()
	{
		System.out.println("Establishing Connection.....");
		SOAP_API_methods.getConnection();

	}

	@Test
	(enabled = true)
	public void finalTests()
	{
		try {
			if(!(SOAP_API_methods.connection == null))
			{
				System.out.println("Connected to SalesForce...");
				SOAP_API_methods.getID(query);
				if(SOAP_API_methods.ContactID == null)
				{
					create_Contact();
					if(!(SOAP_API_methods.ContactID == null))
					{
						update_Contact();
					}
					else
					{
						System.out.println("Record has not created");
					}

				} 
				else 
				{
					System.out.println("Record exists.. Deleting.."); 
					delete_Contact();
				}
			}
			else
			{
				System.out.println("Connection Failed...");
			}
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}

	}

	@Test
	(enabled = false)
	//(priority = 1)
	public void create_Contact() 
	{
		SOAP_API_methods.createContacts();
	}

	@Test
	(enabled = false)
	//(priority = 2)
	public void update_Contact()
	{
		SOAP_API_methods.updateContacts();
	}

	@Test
	(enabled = false)
	//(priority = 3)
	public void delete_Contact()
	{
		SOAP_API_methods.deleteContacts();
	}	

	@AfterTest
	public void afterTest()
	{
		SOAP_API_methods.closeConnection();
	}

}
