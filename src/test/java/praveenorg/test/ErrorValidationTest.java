package praveenorg.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import praveenorg.pajeobjects.CartPage;
import praveenorg.pajeobjects.ProductCatalog;
import praveenorg.testcomponent.BaseTest;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = {"ErrorHandling"})
	public void loginErrorValidation() throws Exception {
		landingPage.loginApplication("praveenhudgi@gmail.com", "Phz@123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void productErrorValidation() throws Exception {
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("praveenhudgi@gmail.com", "Phz@123phz");
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
