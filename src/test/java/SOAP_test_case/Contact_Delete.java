package SOAP_test_case;

import com.sforce.ws.ConnectionException;

import SOAP_WSDL.SOAP_API_methods;

public class Contact_Delete {

	public static void main(String[] args) 
	{
		SOAP_API_methods.getConnection();
		SOAP_API_methods.deleteContacts();
		SOAP_API_methods.closeConnection();

	}

}
