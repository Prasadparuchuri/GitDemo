package WorkingFW;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Submit {
    public static void main(String[] args) throws InterruptedException {

        String productName = "ADIDAS ORIGINAL";

        // Setup browser
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Landing page → login
        LandingPageS landingPageS = new LandingPageS(driver);
        landingPageS.goTo();
        ProductCatalogueS productCatalogue = landingPageS.loginApplication(
                "prasad183@gmail.com",
                "Rgukt@161183"
        );

        // Add product to cart
        productCatalogue.addProductToCart(productName);

        // Navigate to CartPage
        CartPageS cartPage = productCatalogue.goToCartPage();

        // Verify product in cart
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match, "Product not found in cart!");

        // Checkout
        CheckoutPageS checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        ConfirmationPageS confirmationPage = checkoutPage.submitOrder();

        // Confirmation assertion
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.toLowerCase().contains("thank you"), "Order not placed successfully!");

        driver.close();
    }
}
