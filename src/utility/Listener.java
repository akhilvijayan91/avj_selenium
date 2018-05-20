package utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public  class Listener extends TestListenerAdapter					
{		

	 public WebDriver driver;
	 ExtentReports reports;
	 ExtentTest test;

     public void onTestStart(ITestResult result) {
    	  System.out.println("on test start");
    	  test = reports.startTest(result.getMethod().getMethodName());
    	  test.log(LogStatus.INFO, result.getMethod().getMethodName() + "test is started");
    	 }
    	 public void onTestSuccess(ITestResult result) {
    	  System.out.println("on test success");
    	  test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");
    	 }
    	 public void onTestFailure(ITestResult result) {
    	  System.out.println("on test failure");
    	  test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed");
    	  TakesScreenshot ts = (TakesScreenshot) driver;
    	  File src = ts.getScreenshotAs(OutputType.FILE);
    	  try {
    	   FileUtils.copyFile(src, new File(".\\screenshots" + result.getMethod().getMethodName() + ".png"));
    	   String file = test.addScreenCapture(".\\screenshots" + result.getMethod().getMethodName() + ".png");
    	   test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", file);
    	   test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", result.getThrowable().getMessage());
    	  } catch (IOException e) {
    	   e.printStackTrace();
    	  }
    	 }
    	 public void onTestSkipped(ITestResult result) {
    	  System.out.println("on test skipped");
    	  test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");
    	 }
    	 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    	  System.out.println("on test sucess within percentage");
    	 }
    	 public void onStart(ITestContext context) {
    	  System.out.println("on start");
    	  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy h-m-s");
    	    Date date = new Date();
    	    String className = this.getClass().getSimpleName();
    	    //System.setProperty("webdriver.chrome.driver","E:\\AKHIL_PROJECT\\Akhil\\chromedriver.exe");
    	 // driver = new ChromeDriver(); // Set the drivers path in environment variables to avoid code(System.setProperty())
    	  //reports = new ExtentReports(System.getProperty("user.dir") +"\\test-output\\Extent Reports\\"+className+"_"+dateFormat.format(date)+"ExtentReport.html");
    	 //reports=new ExtentReports(System.getProperty("user.dir") +"\\extentreports\\"+className+"_"+dateFormat.format(date)+"ExtentReport.html", false);
    	 reports = new ExtentReports(System.getProperty("user.dir") +"\\extentreports\\"+className+"_"+dateFormat.format(date)+"ExtentReport.html", false);
    	 }
    	 public void onFinish(ITestContext context) {
    	  System.out.println("on finish");
    	  
    	  reports.endTest(test);
    	  reports.flush();
    	  driver.close();
    	 }
    	}
