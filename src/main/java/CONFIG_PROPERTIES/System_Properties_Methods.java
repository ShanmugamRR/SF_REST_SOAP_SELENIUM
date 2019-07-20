package CONFIG_PROPERTIES;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class System_Properties_Methods 

{

	public static Properties getInput() 
	{
		try
		{
			InputStream Input = new FileInputStream(".//resources//system.properties");
			Properties prop = new Properties();
			prop.load(Input);
			return prop;
		}
				
		catch(IOException io)
		{
			io.printStackTrace();
		}
		return null;
		
		
	}
	
}
