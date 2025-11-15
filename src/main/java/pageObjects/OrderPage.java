package pageObjects;

import Test.AbstratComponent.AbstratComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstratComponent
{
    WebDriver driver;

    @FindBy(css=".totalRow button")
    WebElement checkoutEle;

    @FindBy(css=".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css="tr td:nth-child(3)")
    private List<WebElement> productNames;

    public OrderPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public Boolean VerifyOrderDisplay(String productName)
    {
        Boolean match=productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
        return match;
    }

    public boolean VerifyProductDisplay(String productName)
    {
        Boolean match=cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
        return match;
    }
    public CheckoutPage goToCheckout()
    {
        checkoutEle.click();
        return new CheckoutPage(driver);
    }

}
