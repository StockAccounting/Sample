package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil 
{

	public static String getValueForKey(String key) throws Throwable, Exception
	{
		Properties confingProperties=new Properties();
confingProperties.load(new FileInputStream(new File("D:\\Vasu\\StockAccounting\\PropertiesFile\\Environment.properties")));
		return confingProperties.getProperty(key);
		
	}
}
