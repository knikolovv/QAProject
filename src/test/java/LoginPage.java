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

    public void login(String email, String pass) {
        enterEmail(email);
        enterPassword(pass);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public SearchPage loginToSearchPage(String email, String pass) {
        login(email, pass);
        return new SearchPage();
    }

    public void loginExpectingFailure(String email, String pass) {
        login(email, pass);
        wait.until(ExpectedConditions.invisibilityOf(alertMessage));
    }

    public TicketSelectionPage loginToTicketSelectionPage(String email, String pass) {
        login(email, pass);
        return new TicketSelectionPage();
    }

    public SearchPage logout() {
        logoutButton.click();
        return new SearchPage();
    }

    public void cancelLogin() {
        cancelLoginButton.click();
    }

    public void clickRegisterButton() {
        registerButton.click();
        wait.until(ExpectedConditions.urlToBe("https://bileti.bdz.bg/register"));
    }

    public void clickForgottenPassword() {
        forgotPasswordButton.click();
        wait.until(ExpectedConditions.urlToBe("https://bileti.bdz.bg/forgot-password"));

    }

}
