package WorkingFW;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPageS extends AbstractComponentS {
    WebDriver driver;

    public LandingPageS(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement passwordEle;

    @FindBy(id = "login")
    WebElement submit;

    public ProductCatalogueS loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();

        return new ProductCatalogueS(driver);
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
