package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
String inputpath ="./FileInput/LoginData.xlsx";
String outputpath="./FileOutPut/DataDrivenResults.xlsx";
@Test
public void startTest()throws Throwable
{
	
	//create reference object for Excelfile util
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of row in login sheet
	int rc =xl.rowCount("Login");
	Reporter.log("No of rows are::"+rc,true);
	//iterate all rows in Login sheet
	for(int i=1;i<=rc;i++)
	{
		//read username and password cells
		String user =xl.getCellData("Login", i, 0);
		String pass =xl.getCellData("Login", i, 1);
		//call login method
		boolean res =FunctionLibrary.check_Login(user, pass);
		if(res)
		{
			//write as login success into results cell
			xl.setCellData("Login", i, 2, "Login Success", outputpath);
			xl.setCellData("Login", i, 3, "Pass", outputpath);
			
		}
		else
		{
			File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./ScreenShot/Iterattion/"+i+"Loginpage.png"));
			//if res is true capture error message and write into results cell
			String Error_message =driver.findElement(By.xpath(conpro.getProperty("ObjErrormessage"))).getText();
			xl.setCellData("Login", i, 2, Error_message, outputpath);
			xl.setCellData("Login", i, 3, "Fail", outputpath);
		}
	}
	
}
}
