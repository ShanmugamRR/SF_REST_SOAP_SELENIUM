package SOAP_test_case;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sforce.ws.ConnectionException;

import REST_HTTP.API_Variables;
import SOAP_WSDL.SOAP_API_methods;

public class Contact_Delete implements API_Variables
{

	@BeforeTest
	public void getConnection()
	{
		System.out.println("Establishing Connection.....");
		SOAP_API_methods.getConnection();
	}
	
	@Test
	public void Delet_Contact()
	{
		try {
			if(!(SOAP_API_methods.connection == null))
			{
				System.out.println("Connected to SalesForce...");
				SOAP_API_methods.getID(query);
				if(!(SOAP_API_methods.ContactID == null))
				{
					SOAP_API_methods.deleteContacts();
				}
				else
				{
					System.out.println("No Records to delete...");
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
