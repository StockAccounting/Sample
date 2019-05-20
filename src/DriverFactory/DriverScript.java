package DriverFactory;

import org.openqa.selenium.WebDriver;

import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript 
{
	public static WebDriver driver;
	public void startTest() throws Throwable
	{
	
		ExcelFileUtil excel=new ExcelFileUtil();
	
		for (int i = 1; i <=excel.rowCount("MasterTestCases"); i++)
		{
			if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
			
				//Define Module Name
				String TCModule=excel.getData("MasterTestCases", i, 1);
			
				int rowcount=excel.rowCount(TCModule);
				//System.out.println(rowcount);
				for (int j = 1; j <=rowcount; j++) 
				{
					String Description=excel.getData(TCModule, j, 0);
					String Object_Type=excel.getData(TCModule, j, 1);
					//System.out.println(Object_Type);
					String Locator_Type=excel.getData(TCModule, j, 2);
					String Locator_Value=excel.getData(TCModule, j, 3);
					String Test_Data=excel.getData(TCModule, j, 4);
				
					if(Object_Type.equalsIgnoreCase("startBrowser"))
					{
						driver=FunctionLibrary.startBrowser(driver);
					}
					if(Object_Type.equalsIgnoreCase("openApplication"))			
					{
						FunctionLibrary.openApplication(driver);
					}
					if(Object_Type.equalsIgnoreCase("clickAction"))
					{
						FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
					}
					if(Object_Type.equalsIgnoreCase("typeAction"))
					{
						FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
					}
					if(Object_Type.equalsIgnoreCase("closeBrowser"))
					{
						FunctionLibrary.closeBrowser(driver);
					}
					if (Object_Type.equalsIgnoreCase("waitForElement")) 
					{
						FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
					}
					
				}
			}
		}	
		
	}
}
	


