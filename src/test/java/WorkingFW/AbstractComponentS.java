package WorkingFW;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponentS {
    WebDriver driver;

    public AbstractComponentS(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Wait until element located by By is visible
    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    // Wait until a WebElement is clickable
    public void waitForElementToBeClickable(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    // Wait until WebElement disappears
    public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    // Navigate to cart page
    public CartPageS goToCartPage() {
        By cartHeader = By.cssSelector("[routerlink*='/dashboard/cart']");
        waitForElementToAppear(cartHeader);
        driver.findElement(cartHeader).click();
        return null;
    }
}

