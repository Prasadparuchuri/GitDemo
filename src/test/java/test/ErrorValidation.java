package test;
import Fw3.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LandingPage;

import java.io.IOException;

public class ErrorValidation extends BaseTest
{
    //public LandingPage landingPage;

    @Test(groups={"ErrorHandling"})

    public void submitOrder() throws IOException ,InterruptedException
    {


        String productName="ZARA COAT 3";
        landingPage.loginApplication("prasad183@gmail.com","Rgukt@161183");
        Assert.assertEquals("Incorrect email or password"),landingPage.getErrorMessage());





    }
}
