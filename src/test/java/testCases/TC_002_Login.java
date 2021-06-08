package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_002_Login extends BaseClass
{
	@Test(groups= {"sanity","master"})
	public void test_login()
	{
		logger.info("Started TC_002_Login");
		try 
		{
			driver.get(rb.getString("appURL"));
			logger.info("HomePage displayed");
			
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("MyAccount clicked");
			hp.clickLogin();
			logger.info("Login link clicked");
			
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(rb.getString("email"));
			logger.info("email entered");
			lp.setPassword(rb.getString("password"));
			logger.info("password entered");
			lp.clickbtnLogin();
			logger.info("login button clicked");
			
			boolean targetPage=lp.isMyAccountPageExist();
			if(targetPage)
			{
				logger.info("Login successfull");
				Assert.assertTrue(true);
			}
			else
			{
				logger.info("Login failed");
				captureScreen(driver,"test_login");
				Assert.fail();
			}
		}
		catch (Exception e)
		{
			logger.fatal("Login failed");
			Assert.fail();
		}
		
		logger.info("Finished TC_002_Login");
	}

}
