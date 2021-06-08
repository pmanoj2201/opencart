package testBase;

import java.io.File;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 
{
	public WebDriver driver;
	public Logger logger;
	public ResourceBundle rb;
	
	@BeforeClass(groups= {"sanity","master","regression"})              //change JRE to 1.8
	@Parameters({"browser"})
	public void setup(String br)
	{
		// for configuration..
		rb=ResourceBundle.getBundle("config");
		logger=LogManager.getLogger(this.getClass());
		
		if(br.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			logger.info("chrome browser is launched");
		}
		else if(br.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
			logger.info("edge browser is launched");
		}
		else if(br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			logger.info("firefox browser is launched");
		}
		
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
		
	@AfterClass(groups= {"sanity","master","regression"})
	public void teardown()
	{
		driver.close();
	}
	
	public String randomString() 
	{
		String generateString=RandomStringUtils.randomAlphabetic(5);
		return (generateString);
		
	}
	
	public int randomNumber() 
	{
		String generateNumber=RandomStringUtils.randomNumeric(5);
		return (Integer.parseInt(generateNumber));
		
	}
	
	public void captureScreen(WebDriver driver, String tname) throws Exception
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target= new File(System.getProperty("user.dir")+"\\screenshots\\"+tname+".png");
		FileUtils.copyFile(source, target);
		
	}

}
