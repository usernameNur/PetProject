package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CartPage {
    public CartPage() {
        PageFactory.initElements(Driver.getDriver(), LoginPage.class);

    }
    @FindBy(xpath="//button[@id='checkout']")
    public WebElement checkoutButton;
    


}
