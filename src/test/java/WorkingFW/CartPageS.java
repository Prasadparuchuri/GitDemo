package WorkingFW;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPageS extends AbstractComponentS {
    WebDriver driver;

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    By cartProductsBy = By.cssSelector(".cartSection h3");

    public CartPageS(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean VerifyProductDisplay(String productName) {
        waitForElementToAppear(cartProductsBy);
        return cartProducts.stream()
                .anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPageS goToCheckout() {
        waitForElementToBeClickable(checkoutEle);
        checkoutEle.click();
        return new CheckoutPageS(driver);
    }
}


