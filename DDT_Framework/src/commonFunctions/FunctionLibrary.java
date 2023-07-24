package commonFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
	public static boolean check_Login(String username,String password)
	{
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(password);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		String Expected ="dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			return true;
			}
		else
		{
			return false;
		}
		
	}

}
