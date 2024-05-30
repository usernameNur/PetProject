package Tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utilities.Config;
import utilities.Driver;

public class TestClass {
    @Test
    public void  navigate(){
        Driver.getDriver().get(Config.getProperties("sauceDemo"));
    }
}
