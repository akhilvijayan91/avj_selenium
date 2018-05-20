package utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.testng.TestNG;


import com.google.common.collect.Lists;

import pages.loginPage;

public class StartTest {
	
	public static void main(String args[]) throws Exception{
		Object[][] testdata=null;
		testdata=testdatagenerator();
		writeToFile(testdata);
		System.out.println("Done");
		testngcall();
	}
	
	public static boolean writeToFile(Object[][] testdata){
		try{/*
		String mycontent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
				  "<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">"+
				  	"<suite name=\"Suite\">"+
				  	"<test name=\"Test\">"+
    "<classes> <class name=\"testcases.Tc_01_validateLogin\"> </classes>"+
  "</test> <!-- Test --></suite> <!-- Suite -->";
			         //Specify the file name and path here
				 File file = new File("./testng.xml");

				  This logic will make sure that the file 
				  * gets created if it is not present at the
				  * specified location
				  if (!file.exists()) {
				     file.createNewFile();
				  }

				  FileWriter fw = new FileWriter(file);
				  BufferedWriter bw = new BufferedWriter(fw);
				  bw.write(mycontent);
				  return true;
		*/
			
			Element root=new Element("suite");
			root.setAttribute("name","Suite");
			Document doc=new Document();

			Element child1=new Element("test");
			child1.setAttribute("name","test");
			Element child11=new Element("classes");
			
			int i=0;
			
			while(null!=testdata[i][0]&&null!=testdata[i][1]&&"Yes".equals(testdata[i][1].toString().trim())){
			Element child111=new Element("class");
			child111.setAttribute("name",(String) testdata[i][0]);
			child11.addContent(child111);
			i++;
			}
		
			child1.addContent(child11);
			root.addContent(child1);

			doc.setRootElement(root);

			XMLOutputter outter=new XMLOutputter();
			outter.setFormat(Format.getPrettyFormat());
			outter.output(doc, new FileWriter(new File("./testng.xml")));
		
		}catch(IOException e){
			System.out.print("Xml writing failed");
		}
		return true;		  
	}
	
	
	public static Object[][] testdatagenerator() throws Exception
	{
		FileInputStream fis = new FileInputStream("./TestData/TestCase.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("testcase");
        int noofrows=sheet.getPhysicalNumberOfRows();
        XSSFRow row=sheet.getRow(0);
       
        Object[][] testdata=new Object[noofrows-1][2];
        for(int i=1;i<noofrows;i++)
        {
        	  XSSFRow nrow=sheet.getRow(i);
        	  if(nrow!=null) {
        	
        	    
        	//  XSSFCell slno= nrow.getCell(0);
        	
        	  
        	  XSSFCell testCaseName= nrow.getCell(1);
        	  XSSFCell testCaseFlag= nrow.getCell(3);
        	  if(testCaseName!=null&&testCaseFlag!=null){
         	  String name=testCaseName.getStringCellValue();
         	  String flag=testCaseFlag.getStringCellValue(); 
         	  
         	  if(!name.isEmpty()&&!flag.isEmpty()){
         		 testdata[i-1][0]=name;
         	     testdata[i-1][1]=flag;
         	  }
        	  }
        	  
        	  

        }
        }
        return testdata;
	
	
	}
	public static void testngcall()
	{
		TestNG testng=new TestNG();
		List<String> suites=Lists.newArrayList();
		
		suites.add("testng.xml");
		testng.setTestSuites(suites);
		testng.run();
	}
	}


