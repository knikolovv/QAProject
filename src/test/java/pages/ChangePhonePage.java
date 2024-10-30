package pages;

import io.qameta.allure.Step;
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

    @Step("Enter new phone number")
    public void enterPhoneNumber(String number) {
        inputText(changePhoneNumberField, number);
    }

    @Step("Click the button to save the new phone and return to the profile page")
    public ProfilePage clickChangePhoneButton() {
        changePhoneNumberButton.click();
        return new ProfilePage();
    }

}
