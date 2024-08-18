import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends BaseTest {

    // TODO RETURN IN LOGINPAGE


    //TODO ASK navqskude izpolzvam div s id header controls, moga li da go sukratq ?
    @FindBy(xpath = "//div[@id='header-controls']//a[@href='/login']")
    public WebElement loginHeaderButton;

    @FindBy(xpath = "//div[@id='header-controls']//a[@href='/register']")
    public WebElement registerHeaderButton;

    @FindBy(xpath = "//div[@id='header-controls']//button[@class='control-language']")
    public WebElement langHeaderButton;

    @FindBy(id = "header-logo")
    public WebElement logo;

    // TODO ASK Don't know if it should be made in separate class/page
    @FindBy(xpath = "//div[@id='header-controls']//a[@href='/profile']")
    public WebElement profileButton;

    @FindBy(xpath = "//div[@id='header-controls']//button[@class='control-exit-profile']")
    public WebElement logoutButton;

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public void verifyText(String property, String expectedText, String actualText) {
        assert actualText.equals(expectedText) : "Expected " + property + ": " + expectedText + " but found: " + actualText;
    }

//    public void verifyPageIsOpen() {
//        wait.until(ExpectedConditions.visibilityOf());
//    }

    public void inputText(WebElement element, String text) {
        // element.clear() alternative
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void changeLanguage() {
        langHeaderButton.click();
    }

    // TODO RETURN IN LOGINPAGE

}
