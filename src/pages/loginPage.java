  package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.utilityFetchproperty;

public class loginPage {
	
	WebDriver driver;
	/*Creating a constructor*/
	public loginPage(WebDriver driver)
	{/*when some one going to create object of this login page class he or she will pass the driver object.
	 When are creating the object the constructor is automatically called,so the driver which is passed will be stored
	 in public loginPage(WebDriver driver),now we will be passing this driver value to global object and global object 
	 can be used in all the methods*/
		this.driver=driver;
	}

	public void enterusername(String username) throws IOException
	{
		 driver.findElement(By.xpath(utilityFetchproperty.fetchlocatorvalue("login_username_xpath"))).sendKeys(username);
	}
	public void enterpassword(String password) throws IOException
	{
		 driver.findElement(By.xpath(utilityFetchproperty.fetchlocatorvalue("login_password_xpath"))).sendKeys(password);
	        
	        
	}
	public void clicksignin() throws IOException
	{
		 
	        driver.findElement(By.xpath(utilityFetchproperty.fetchlocatorvalue("login_signin_xpath"))).click();
}
}