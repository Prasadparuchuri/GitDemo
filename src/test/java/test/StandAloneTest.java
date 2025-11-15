package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.List;

public class StandAloneTest
{
    public static void main(String[] args)
    {

        String productName="ADIDAS ORIGINAL";
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        driver.findElement(By.id("userEmail")).sendKeys("prasad183@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Rgukt@161183");
        driver.findElement(By.id("login")).click();



        List<WebElement> products = driver.findElements(By.cssSelector(".col-sm-10"));
       // products.stream().filter(product->product.getText().equals("ADIDAS ORIGINAL"));
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-sm-10")));

        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOgfElementLocated(By.cssSelector(".ng-animating")));
          driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();



        List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();


        Actions a=new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
        //String confirmMessage=driver.findElement(By.cssSelector("$('.hero-primary')\n")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("thank you for the order"));

        driver.close();




    }
}
