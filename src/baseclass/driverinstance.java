package baseclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utility.utilityFetchproperty;
public class driverinstance {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void initiateDriverinstance() throws Exception
	{/* fetching data from property file converting object to string and then comparing*/
		if(utilityFetchproperty.fetchpropertyvalue("browserName").toString().equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			
		driver=new ChromeDriver();
		}
		
		else if(utilityFetchproperty.fetchpropertyvalue("browserName").toString().equalsIgnoreCase("firefox"))
		{
			
			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
			driver= new FirefoxDriver();
		}
		else if(utilityFetchproperty.fetchpropertyvalue("browserName").toString().equalsIgnoreCase("internetexplorer"))
		{
			driver=new InternetExplorerDriver();
		}
		else
		{
			
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			driver=new ChromeDriver();
		}
		driver.get(utilityFetchproperty.fetchpropertyvalue("URL").toString());
	}


	@AfterMethod
	public void closeDriverInstance()
	{ 
		driver.close();
	}
}
	