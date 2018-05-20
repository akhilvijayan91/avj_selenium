package testcases;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import assertions.compare;
import baseclass.driverinstance;
import pages.loginPage;
import utility.Listener;
@Listeners(utility.Listener.class)
/*Extends the parent class.when we are executing the test case first it will check for test annotation,now its checks 
 * do we have before method annotation.Since we are inheriting another class it will go to the parent class and checks 
 * if we have before annotation,so before method will execute and create a driver instance.since in parent driver is
 * made as public it can be used in child class
 * */
public class Tc_01_validateLogin extends driverinstance {
	
	@Test(dataProvider="Excel")
	
	public void tc_01_login(String uname, String pass) throws Exception
	{/*loinpage class has a constructor which needs driver object so we are passing the driver object from here and the value
	 	will be coming from parent due to inheritance*/
		loginPage login=new loginPage(driver);
		login.enterusername(uname);
		login.enterpassword(pass);
		login.clicksignin();
		String expectedtitle= "Find a Flight: Mercury Tours:";
		compare compare= new compare();
		compare.validatepagetitle(driver, expectedtitle);
	}
	@DataProvider(name="Excel")
	public Object[][] testdatagenerator() throws Exception
	{
		FileInputStream fis = new FileInputStream("./TestData/sampletestdata.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("credentials");
        int noofrows=sheet.getPhysicalNumberOfRows();
        XSSFRow row=sheet.getRow(0);
        int noofcolumns=row.getLastCellNum();
        Object[][] testdata=new Object[noofrows-1][noofcolumns];
        for(int i=1;i<noofrows;i++)
        {
        	  XSSFRow nrow=sheet.getRow(i);
        	  if(nrow!=null) {
        	  int minCol = 0;
        	  int maxCol = nrow.getLastCellNum();
        	  for(int j=minCol ; j<maxCol ; j++) 
        	  {
        	    
        	  XSSFCell data= nrow.getCell(j);
        	  //XSSFCell password= nrow.getCell(j+1);
        	  testdata[i-1][j]=data.getStringCellValue();
        	  //testdata[i][j+1]=password.getStringCellValue();
        }
        }
        }
        return testdata;
	}

}
 