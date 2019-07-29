package SOAP_test_case;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import SOAP_WSDL.SOAP_API_methods;

public class Contact_Create {
	
	@BeforeTest
	public void getConnection()
	{
		System.out.println("Establishing Connection.....");
		SOAP_API_methods.getConnection();
	}
	
	@Test
	public void Create_Contact()
	{
		try {
			if(!(SOAP_API_methods.connection == null))
			{
				System.out.println("Connected to SalesForce...");
				SOAP_API_methods.queryContacts();
				if(SOAP_API_methods.ContactID == null)
				{
					SOAP_API_methods.createContacts();
				}
				else 
				{
					System.out.println("Record exists.. Deleting and Creating Contact.."); 
					SOAP_API_methods.deleteContacts();
					SOAP_API_methods.createContacts();
				}
			}
		} catch (Exception E)
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
