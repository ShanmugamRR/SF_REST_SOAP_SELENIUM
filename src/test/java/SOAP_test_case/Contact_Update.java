package SOAP_test_case;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import REST_HTTP.API_Variables;
import SOAP_WSDL.SOAP_API_methods;

public class Contact_Update implements API_Variables
{

	@BeforeTest
	public void getConnection()
	{
		System.out.println("Establishing Connection.....");
		SOAP_API_methods.getConnection();
	}
	
	@Test
	public void Update_Contact()
	{
		try {
			if(!(SOAP_API_methods.connection == null))
			{
				System.out.println("Connected to SalesForce...");
				SOAP_API_methods.getID(query);
				if(!(SOAP_API_methods.ContactID == null))
				{
					SOAP_API_methods.updateContacts();
				}
				else
				{
					System.out.println("Records doesn't eixst...");
				}
			}
		} catch(Exception E)
		{
			E.printStackTrace();
		}
	}

	@AfterTest
	public void closeConnection()
	{
		SOAP_API_methods.closeConnection();
	}

}
