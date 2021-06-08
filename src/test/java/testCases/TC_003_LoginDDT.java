package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.XLUtility;

public class TC_003_LoginDDT extends BaseClass
{
	@Test(dataProvider="LoginData")
	public void test_LoginDDT(String email, String password, String exp)
	{
		logger.info("started TC_003_LoginDDT");
		
		try
		{
			driver.get(rb.getString("appURL"));
			logger.info("HomePage is displayed");
			driver.manage().window().maximize();
			
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("MyAccount Clicked");
			hp.clickLogin();
			logger.info("Login clicked");
			
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			logger.info("email entered");
			lp.setPassword(password);
			logger.info("password entered");
			lp.clickbtnLogin();
			logger.info("login button clicked");
			
			boolean targetPage=lp.isMyAccountPageExist();
			if(exp.equals("Valid"))
			{
				if(targetPage==true)
				{
					logger.info("login successful!!");
					MyAccountPage myaccpage=new MyAccountPage(driver);
					myaccpage.clickLogout();
					Assert.assertTrue(true);
				}
				else
				{
					logger.error("login failed!!");
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equals("Invalid"))
			{
				if(targetPage==true)
				{
					logger.info("login successful with invalid data.. test failed" );
					MyAccountPage myaccpage=new MyAccountPage(driver);
					myaccpage.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					logger.error("login failed");
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e)
		{
			logger.fatal("login failed");
			Assert.fail();
		}
		logger.info("finished TC_003_LoginDDT");
	}

	
	@DataProvider(name="LoginData")
	public String[][]getData() throws Exception
	{
		String path=".\\testdata\\Opencart_LoginData.xlsx";
		XLUtility xlutil=new XLUtility(path);
		int totalrows=xlutil.getRowCount("sheet1");
		int totalcols=xlutil.getCellCount("sheet1", 1);
		String logindata[][]=new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		return logindata;
		
	}

}
