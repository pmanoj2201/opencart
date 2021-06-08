package testCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass
{
	
	
	@Test(groups= {"regression","master"})
	public void test_account_registration()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.info("Registration test started");
		try
		{
			//driver.get("https://demo.opencart.com/");
			driver.get(rb.getString("appURL"));
			driver.manage().window().maximize();
			logger.info("HomePage displayed");
			
			HomePage hp= new HomePage(driver);
			hp.clickMyAccount();
			hp.clickRegister();
			
			logger.info("Register Page displayed");
			
			AccountRegisterPage actreg=new AccountRegisterPage(driver);
			
			actreg.setFirstName("Oliver");
			logger.info("First Name Entered");
			actreg.setLastName("Queen");
			logger.info("Last Name Entered");
			actreg.setEMail(randomString()+"@gmail.com");
			logger.info("EMail Entered");
			actreg.setTelphone("0099887766");
			logger.info("Telephone Entered");
			actreg.setPassword("OQ1234");
			logger.info("Password Entered");
			actreg.setCnfPassword("OQ1234");
			logger.info("Password Confirmed");
			actreg.selectagreeConditions();
			logger.info("Conditions Agreed");
			actreg.clickContinue();
			
			String confmsg=actreg.getConfirmationMsg();
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
				logger.info("Account created successfully");
			}
			else
			{
				logger.info("Account creation failed");
				captureScreen(driver,"test_account_registration");
				Assert.assertTrue(false);
				
			}
		}
		catch(Exception e)
		{
			logger.info("Account creation failed");
			Assert.fail();
		}
		
		logger.info("Registration test finished");
	}
	
	
	

}
