import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    // TODO don't know if it has to be here or in test class

    @FindBy(id = "id-email")
    public WebElement loginEmailInput;
    @FindBy(id = "id-password")
    public WebElement loginPasswordInput;

    @FindBy(xpath = "//div[contains(@class, 'col-md-offset-5 col-md-4')]//button")
    public WebElement loginButton;

    public void enterEmail(String email) {
        inputText(loginEmailInput,email);
    }

    public void enterPassword(String pass) {
        inputText(loginPasswordInput,pass);
    }

    public void login(String email, String pass) {
        enterEmail(email);
        enterPassword(pass);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public SearchPage logout() {
        logoutButton.click();
        return new SearchPage();
    }
}
