package com.project.www.stepdefs;

import com.project.www.pages.LoginPage;
import com.project.www.pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {

    private LoginPage loginPage;

    public LoginPageSteps() {
        loginPage = PagesFactory.getInstance().getLoginPage();
    }

    @Given("the user is on the login page {string}")
    public void theUserIsOnTheLoginPage(String url) {
        loginPage.navigateTo(url);
    }

    @When("the user introduces the username {string}")
    public void theUserIntroducesTheUsername(String username) {
        loginPage.enterUsername(username);
    }

    @And("the user introduces the password {string}")
    public void theUserIntroducesThePassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("the user clicks on the login button")
    public void theUserClicksOnTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("the user is logged in")
    public void theUserIsLoggedIn() {
    }
}
