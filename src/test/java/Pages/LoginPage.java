package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage {
    // End to End Test Case
    //Given sauceDemo.com
    //When User Logs in with correct Credentials
    //Then Verify User sees HomePage
    //Then User clicks on "add" Jacket button
    //Then Verify "add" button changes to "Remove" button
    //Then Verify "1" number appears on a cart
    //Then User Clicks on "Cart"
    //Then verify you redirected to cart Page url
    //Then Verify User sees product in a Cart Page
    //Then Verify "Checkout" button in the Cart Page
    //Then User clicks on "Checkout" button
    //Then verify you redirected to User Input Page url
    //Then Verify User sees User input Field
    //Then User provides User Info
    //Then User clicks on "Continue" button
    //Then verify you redirected to current url
    //Then Verify User sees Payment information on that page
    //Then User clicks on "Finish" button
    //Then verify you redirected to current Page url
    //Then Verify "Thank you for your order" message
    //Then User Click on "HomePage" button
    //Then verify you redirected to cart Home Page url
    //Then Verify User sees product list in Product Page



    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), LoginPage.class);
    }
    @FindBy(name="user-name")
    public WebElement username;
    @FindBy(name="password")
    public WebElement password;
    @FindBy(name="login-button")
    public WebElement loginButton;

    @FindBy(xpath="//button[@class='error-button']")
    public WebElement invalidLoginMessage;

    public void  login(String userName, String pwd){
        username.sendKeys(userName);
        password.sendKeys(pwd);
        loginButton.click();
    }

}









