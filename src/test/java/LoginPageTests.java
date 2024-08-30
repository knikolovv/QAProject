import org.junit.jupiter.api.Test;

public class LoginPageTests extends BaseTest {

    @Test
    public void loginTest() {
        LoginPage loginPage = LoginPage.open();

        // Step - Login with incorrect data
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
    public void loginPageButtonsTest() {
        LoginPage loginPage = LoginPage.open();

        loginPage.cancelLogin();

        LoginPage.open().clickRegisterButton();

        LoginPage.open().clickForgottenPassword();
    }

}