package org.example.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.*;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest
{
    public static void main(String[] args) throws InterruptedException
    {

        String productName="ADIDAS ORIGINAL";
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        LandingPage landingPage= new LandingPage(driver);
        landingPage.goTo();
        ProductCatalogue productCatalogue=landingPage.loginApplication("prasad183@gmail.com","Rgukt@161183");

      // ProductCatalogue productCatalogue=new ProductCatalogue(driver);
       List<WebElement> products=productCatalogue.getProductList();
       productCatalogue.addProductToCart(productName);
       productCatalogue.goToCartPage();


        CartPage cartPage=new CartPage(driver);
        Boolean match=cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage=cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage=checkoutPage.submitOrder();
        String confirmMessage=confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("thank you for the order"));

        driver.close();




    }
}
