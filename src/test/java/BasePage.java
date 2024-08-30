import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
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
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @FindBy(xpath = "//div[@id='header-controls']//button[@class='control-language']")
    private WebElement langHeaderButton;

    @FindBy(xpath = "//div[@id='header-controls']//a[@href='/profile']")
    private WebElement profileButton;

    /**
     * Default constructor (used for pages)
     */
    public BasePage() {
        PageFactory.initElements(driver, this);
        verifyPageIsOpen();
    }

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
        // element.clear() alternative
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    public void changeLanguage() {
        langHeaderButton.click();
    }

    protected ProfilePage openProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton));
        profileButton.click();
        return new ProfilePage();
    }

}
