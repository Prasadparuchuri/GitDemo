package test;

import Fw3.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest3 extends BaseTest
{
    String productName="ZARA COAT 3";
    @Test
    public void submitorder() throws IOException
    {

        LandingPage landingPage=launchApplication();
        ProductCatalogue productCatalogue=landingPage.loginApplication("prasad183@gmail.com","Rgukt@161183");
        List<WebElement> products=productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage=productCatalogue.goToCartPage();

        Boolean match=cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage=cartPage.goToCheckout();
        ConfirmationPage confirmationPage=checkoutPage.submitOrder();
        String confirmMessage=confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER"));
        driver.close();

    }
    @Test(dependsOnMethods = {"submitorder"})
    public void orderHistoryTest()
    {
        ProductCatalogue productCatalogue=landingPage.loginApplication("prasad183@gmail.com","Rgukt@161183");
        OrderPage orderPage=productCatalogue.goToOrdersPage();

        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }


}
