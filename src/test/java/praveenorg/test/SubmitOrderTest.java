package praveenorg.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import praveenorg.pajeobjects.CartPage;
import praveenorg.pajeobjects.CheckoutPage;
import praveenorg.pajeobjects.ConfirmationPage;
import praveenorg.pajeobjects.OrderPage;
import praveenorg.pajeobjects.ProductCatalog;
import praveenorg.testcomponent.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups="Purchase")
	public void submitOrder(HashMap<String,String> input) throws Exception {

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalog productCatalog = landingPage.loginApplication("praveenhudgi@gmail.com", "Phz@123phz");
		OrderPage ordersPage = productCatalog.goToOrderPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));

	}

	
	public File getScreenshot(String testcaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" + testcaseName + ".png");
		FileUtils.copyFile(source, file);
		return file;
	}
	@DataProvider
	public Object[][] getData() throws Exception {

		/*
		 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
		 * "praveenhudgi@gmail.com"); map.put("password", "Phz@123phz");
		 * map.put("product", "ZARA COAT 3");
		 * 
		 * HashMap<String,String> map1 = new HashMap<String,String>(); map1.put("email",
		 * "roshangoti@gmail.com"); map1.put("password", "Phz@534phz");
		 * map1.put("product", "ADIDAS ORIGINAL");
		 */
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\praveenorg\\data\\purchase.json");
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}

}
