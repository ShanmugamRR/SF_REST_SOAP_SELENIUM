package CONFIG_PROPERTIES;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Create_File_Properties {

	public static void main(String[] args) 
	{
		
		/*try (OutputStream output = new FileOutputStream(".//resources//system.properties"))
		{
			Properties prop = new Properties();
			prop.setProperty("SF_USERNAME", "");
			prop.setProperty("SF_PASSWORD", "");
			
			prop.store(output, null);
			
			System.out.println(prop);
			
		}
		catch(IOException io)
		{
			io.printStackTrace();
		}*/
		System_Properties_Methods sys = new System_Properties_Methods();
		Properties prop = new Properties();
		prop = sys.getInput();
		System.out.println("\nUserName is: "+prop.getProperty("SF_USERNAME"));
	}

}
