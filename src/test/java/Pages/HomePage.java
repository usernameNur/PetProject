package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomePage {
    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), LoginPage.class);
    }
    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    public WebElement backPackButton;
    @FindBy(xpath="//button[@id='remove-sauce-labs-backpack']")
    public WebElement removeBackPack;

    @FindBy(xpath="//span[@data-test='shopping-cart-badge']")
    public WebElement cartBadge;




}
