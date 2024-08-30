import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ChangePhonePage extends BasePage{
    @FindBy(xpath = "//div[@class='col-md-7']//input")
    private WebElement changePhoneNumberField;

    @FindBy(xpath = "//div[contains(@class,'col-md-offset-5')]//button")
    private WebElement changePhoneNumberButton;

    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(changePhoneNumberField));
    }

    public void enterPhoneNumber(String number) {
        inputText(changePhoneNumberField, number);
    }

    public ProfilePage clickChangePhoneButton() {
        changePhoneNumberButton.click();
        return new ProfilePage();
    }

}
