package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(id = "id-email")
    private WebElement loginEmailField;

    @FindBy(id = "id-password")
    private WebElement loginPasswordField;

    @FindBy(xpath = "//div[contains(@class, 'col-md-offset-5 col-md-4')]//button")
    private WebElement loginButton;

    @FindBy(className = "alert")
    private WebElement alertMessage;

    @FindBy(xpath = "//div[@id='header-controls']//button[@class='control-exit-profile']")
    private WebElement logoutButton;

    @FindBy(id = "login-cancel-btn")
    private WebElement cancelLoginButton;

    @FindBy(className = "register-btn")
    private WebElement registerButton;

    @FindBy(className = "login-forgotPassword")
    private WebElement forgotPasswordButton;

    @Step("Open login page")
    public static LoginPage open() {
        driver.get("https://bileti.bdz.bg/login");
        return new LoginPage();
    }

    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(loginPasswordField));
    }

    public void enterEmail(String email) {
        inputText(loginEmailField, email);
    }

    public void enterPassword(String pass) {
        inputText(loginPasswordField, pass);
    }

    @Step("Default user login")
    public void login(String email, String pass) {
        enterEmail(email);
        enterPassword(pass);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    @Step("User login to Pages.SearchPage using {email} and {pass}")
    public SearchPage loginToSearchPage(String email, String pass) {
        login(email, pass);
        return new SearchPage();
    }

    @Step("User tries to login with wrong credentials")
    public void loginExpectingFailure(String email, String pass) {
        login(email, pass);
        wait.until(ExpectedConditions.invisibilityOf(alertMessage));
    }

    @Step("User login to Pages.TicketSelectionPage using {email} and {pass}")
    public TicketSelectionPage loginToTicketSelectionPage(String email, String pass) {
        login(email, pass);
        return new TicketSelectionPage();
    }

    @Step("Log out")
    public SearchPage logout() {
        logoutButton.click();
        return new SearchPage();
    }

    @Step("Cancel login")
    public void cancelLogin() {
        cancelLoginButton.click();
    }

    @Step("Click register button")
    public void clickRegisterButton() {
        registerButton.click();
        wait.until(ExpectedConditions.urlToBe("https://bileti.bdz.bg/register"));
    }

    @Step("Click forgotten password button")
    public void clickForgottenPassword() {
        forgotPasswordButton.click();
        wait.until(ExpectedConditions.urlToBe("https://bileti.bdz.bg/forgot-password"));

    }

}
