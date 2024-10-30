package tests;

import pages.LoginPage;
import pages.ProfilePage;
import pages.SearchPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import utils.Props;

public class LoginPageTests extends BaseTest {

    @Test
    @DisplayName("Login Test")
    @Description("Tests trying to log in with wrong and with correct data")
    public void loginTest() {
        LoginPage loginPage = LoginPage.open();

        loginPage.loginExpectingFailure("wrong1@abv.bg", "Wr0ngPass");

        // Step - Login with correct data
        SearchPage searchPage = loginPage.loginToSearchPage(Props.getEmail(), Props.getPassword());
        searchPage.verifyPageIsOpen();

        // Step - verify profilePage opens
        ProfilePage profilePage = loginPage.openProfile();
        profilePage.verifyPageIsOpen();

        // Step - logout
        searchPage = loginPage.logout();
        searchPage.verifyPageIsOpen();
    }

    @Test
    @DisplayName("Pages.LoginPage buttons test")
    @Description("Tests the rest of the login page functionality besides login")
    public void loginPageButtonsTest() {
        LoginPage loginPage = LoginPage.open();

        loginPage.cancelLogin();

        LoginPage.open().clickRegisterButton();

        LoginPage.open().clickForgottenPassword();
    }

}