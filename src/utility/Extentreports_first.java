package utility;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
public class Extentreports_first extends Screenshotforextentreport
{
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;
     WebDriver driver;   
    @BeforeTest
    public void startReport()
    {
    	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy h-m-s");
    Date date = new Date();
    String className = this.getClass().getSimpleName();
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"\\test-output\\Extent Reports\\"+className+"_"+dateFormat.format(date)+"ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
         
        extent.setSystemInfo("OS","Windows");
        extent.setSystemInfo("Host Name", "Akhil");
        extent.setSystemInfo("Environment", "Staging");
        extent.setSystemInfo("User Name", "Akhil Vijayan");
         
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
        htmlReporter.config().setReportName("Extent Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }
     
    @Test
    public void demoTestPass()
    {  System.out.println("launching Chrome browser"); 
    System.setProperty("webdriver.chrome.driver","D:\\Akhil\\Automation Requirements\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://www.gotechmasters.com/";
    driver.get(baseUrl);
    String expectedTitle = "Techmasters2";
    String actualTitle = driver.getTitle();
    if (actualTitle.contentEquals(expectedTitle)){
        test = extent.createTest("Title Matched", "Test Case Passed");
        Assert.assertTrue(true);
    }
    else
    {
    	test = extent.createTest("Title Mismatch", "Test Case Failed");
        Assert.assertTrue(false);
        
    }
    }
     
    @AfterMethod
    public void getResult(ITestResult result) throws IOException
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            String screenShotPath = Screenshotforextentreport.capture(driver, "screenShotName");
            test.log(Status.FAIL, result.getThrowable());
            test.log(Status.FAIL, "Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));

        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
     
    @AfterTest
    public void tearDown()
    {
        extent.flush();
        driver.close();
    }
}