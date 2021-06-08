package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountRegisterPage 
{
	public WebDriver driver;
	
	public AccountRegisterPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement eMail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement telePhone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement cnfpassword;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	WebElement nwsltrYes;
	
	@FindBy(xpath="//input[@value='0']")
	WebElement nwsltrNo;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement agreeConditions;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continuebtn;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmationmsg;
	
	public void setFirstName(String fname)
	{
		firstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		lastName.sendKeys(lname);
	}
	
	public void setEMail(String email)
	{
		eMail.sendKeys(email);
	}
	
	public void setTelphone(String tel)
	{
		telePhone.sendKeys(tel);
	}
	
	public void setPassword(String pass)
	{
		password.sendKeys(pass);
	}
	
	public void setCnfPassword(String cnfpass)
	{
		cnfpassword.sendKeys(cnfpass);
	}
	
	public void selectagreeConditions()
	{
		agreeConditions.click();
	}
	
	public void clickContinue()
	{
		continuebtn.click();
	}
	
	public String getConfirmationMsg()
	{
		try
		{
			return (confirmationmsg.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}
	
	
}
