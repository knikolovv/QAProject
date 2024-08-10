import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends BaseTest {

    @FindBy(xpath = "//div[@id='header-controls']//a[@href='/login']")
    public WebElement loginControlsButton;
    @FindBy(id = "id-email")
    public WebElement loginEmailInput;
    @FindBy(id = "id-password")
    public WebElement loginPasswordInput;

    @FindBy(xpath = "//div[contains(@class, 'col-md-offset-5 col-md-4')]//button")
    public WebElement loginButton;

    @FindBy(xpath = "//div[@id='header-controls']//a[@href='/register']")
    public WebElement registerControlsButton;

    @FindBy(xpath = "//div[@id='header-controls']//a[@href='/profile']")
    public WebElement profileButton;

    @FindBy(xpath = "//div[@id='header-controls']//button[@class='control-language']")
    public WebElement langControlsButton;

    @FindBy(id = "header-logo")
    public WebElement logo;

    @FindBy(id = "id-search-trips-station-from")
    public WebElement startStationButton;

    @FindBy(id = "react-select-2-input")
    public WebElement startStationInput;

    @FindBy(className = "css-yk16xz-control")
    public WebElement endStationButton;

    @FindBy(id = "react-select-3-input")
    public WebElement endStationInput;

    @FindBy(id = "id-search-trips-going-date")
    public WebElement currentDateButton;

    @FindBy(xpath = "//div[@class='col-md-2']//button")
    public WebElement searchButton;
    @FindBy(xpath = "//input[@class = 'form-control passengers-names\n" +
                    "                        \n" +
                    "                      ']")
    public WebElement nameField;

    @FindBy(xpath = "//div[@class = 'col-sm-4']//select")
    public WebElement genderField;

    @FindBy(xpath = "//div[@class = 'react-datepicker__input-container']//input")
    public WebElement dateField;

    @FindBy(xpath = "//div[@class='col-md-7']//input")
    public WebElement changePhoneNumberField;

    @FindBy(xpath = "//div[@class='col-xs-4']")
    public WebElement profileButtonsSection;

    @FindBy(xpath = "//div[@class='col-md-2']//a")
    public WebElement cancelProfileChangeButton;

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public void verifyText(String property, String expectedText, String actualText) {
        assert actualText.equals(expectedText) : "Expected " + property + ": " + expectedText + " but found: " + actualText;
        System.out.println(property +" is as expected: " + expectedText);
    }

    public void login(String email, String pass) {
        loginEmailInput.clear();
        loginEmailInput.sendKeys(email);
        loginPasswordInput.clear();
        loginPasswordInput.sendKeys(pass);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

}
