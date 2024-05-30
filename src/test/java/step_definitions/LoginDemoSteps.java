package step_definitions;

import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.Config;
import utilities.Driver;

public class LoginDemoSteps {
    LoginPage loginPage=new LoginPage();


    @Given("user is on sauce demo login")
    public void user_is_on_sauce_demo_login() {

        Driver.getDriver().get(Config.getProperties("sauceDemo"));

    }
    @When("user provides a valid user name")
    public void user_provides_a_valid_user_name() {
        loginPage.username.sendKeys(Config.getProperties("sauceUser"));

    }
    @When("user provides valid password")
    public void user_provides_valid_password() {
        loginPage.password.sendKeys(Config.getProperties("saucePassword"));


    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        loginPage.loginButton.click();


    }
    @Then("verify user successfully logged in")
    public void verify_user_successfully_logged_in() {
        Assert.assertEquals(Config.getProperties("sauceDemoProductPage"), Driver.getDriver().getCurrentUrl());

        Driver.getDriver().close();

        Driver.getDriver().quit();

    }

}
