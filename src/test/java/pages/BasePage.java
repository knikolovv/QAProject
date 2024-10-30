package pages;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    public static WebDriver driver;
    protected static WebDriverWait wait;

    @FindBy(xpath = "//div[@id='header-controls']//button[@class='control-language']")
    private WebElement langHeaderButton;
    @FindBy(xpath = "//div[@id='header-controls']//a[@href='/profile']")
    private WebElement profileButton;
    @FindBy(xpath = "//div[@id='header-controls']//a[@href='/login']")
    private WebElement loginHeaderButton;
    @FindBy(xpath = "//div[@id='header-controls']//a[@href='/register']")
    private WebElement registerHeaderButton;
    @FindBy(id = "header-logo")
    private WebElement logo;


    /**
     * Default constructor (used for pages)
     */
    public BasePage() {
        PageFactory.initElements(driver, this);
        verifyPageIsOpen();
    }

    @Step("Verify page is loaded")
    public abstract void verifyPageIsOpen();

    public static void initDriver() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void shutdownDriver() {
        driver.quit();
    }

    public void verifyText(String property, String expectedText, String actualText) {
        assert actualText.equals(expectedText) : "Expected " + property + ": " + expectedText + " but found: " + actualText;
    }

    public void inputText(WebElement element, String text) {
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    @Step("Click language button to change it")
    public void changeLanguage() {
        langHeaderButton.click();
    }

    @Step("Click the login button from the header menu")
    public void clickLoginHeaderButton() {
        loginHeaderButton.click();
    }

    @Step("Click the register button from the header menu")
    public void clickRegisterHeaderButton() {
        registerHeaderButton.click();
    }

    @Step("Click the logo located in the header")
    public void clickLogo() {
        logo.click();
    }

    @Step("Open Profile page")
    public ProfilePage openProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton));
        profileButton.click();
        return new ProfilePage();
    }

}
