package session;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class seleniumpractice {
	WebDriver driver;
	
	
	@Test(dataProvider="facebookdata")
	public void execution(String user,String pass) throws IOException
	{
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mohan raji\\Desktop\\chromedriver.exe");
	 driver=new ChromeDriver();
	driver.get(readfile("url"));
	seleniumpractice.verifylinks(driver);
	driver.findElement(By.id("email")).sendKeys(user);
	driver.findElement(By.id("pass")).sendKeys(pass);
	
		
	}
	
	
	
	
	
	
	
		@DataProvider(name="facebookdata")
	public Object[][] passdata()
	{
	facebookexcel excel=new facebookexcel("D:\\workplace\\seleniumsession\\demo.xlsx");
	int rows=excel.getrownum(0);
	Object[][] pass=new Object[rows][2];
		for(int i=0;i<rows;i++)
		{
			pass[i][0]=excel.getdata(0, i, 0);
			pass[i][1]=excel.getdata(0, i, 1);
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		return pass;
	}
	
	public static void verifylinks(WebDriver driver) throws IOException
	
	{
	List<WebElement>links=driver.findElements(By.tagName("a"));	
	int total=links.size();
		for(int i=0;i<total;i++)
		{
			WebElement ele=links.get(i);
			String links1=ele.getAttribute("href");
			verifylinkactive(links1);
		}
	}
	
	public static void verifylinkactive(String link1) throws IOException
	{
		URL url=new URL(link1);
		HttpURLConnection httpurlconnect=(HttpURLConnection)url.openConnection();
		httpurlconnect.connect();
		httpurlconnect.setConnectTimeout(3000);
		if(httpurlconnect.getResponseCode()==200)
		{
			System.out.println(link1+"-"+httpurlconnect.getResponseMessage()+httpurlconnect.getResponseCode());
		}
		if(httpurlconnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)
		{
			System.out.println(link1+"-"+httpurlconnect.getResponseMessage()+httpurlconnect.getResponseCode());	
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	public static String readfile(String property) throws IOException
	{
		Properties pro=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\webelement.properties");
		pro.load(fis);
		return pro.getProperty(property);
		
		
		
		
	}
	
	public static void screenshot(String screenname,WebDriver driver) throws IOException
	{

	TakesScreenshot ts=(TakesScreenshot)driver;
	File src=ts.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src, new File("./screens/"+screenname+".png"));
	}
	

}
