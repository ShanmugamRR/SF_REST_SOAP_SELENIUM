package SOAP_test_case;

import SOAP_WSDL.SOAP_API_methods;

public class Contact_Create {

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		SOAP_API_methods.getConnection();
		SOAP_API_methods.createContacts();
		SOAP_API_methods.closeConnection();

	}

}
