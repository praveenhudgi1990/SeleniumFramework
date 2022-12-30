package praveenorg.pajeobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import praveenorg.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (id = "userEmail")
	WebElement userName ;
	
	@FindBy (id = "userPassword")
	WebElement passwordEle ;
	
	@FindBy (id = "login")
	WebElement submit ;
	
	@FindBy (xpath = "//*[@aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	
	
	public ProductCatalog loginApplication (String email,String password) {
		
		userName.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		return new ProductCatalog(driver);
	}
	
	
	public void navigateToApplication() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitForElementLocated(errorMessage);
		return errorMessage.getText();
	}

}
