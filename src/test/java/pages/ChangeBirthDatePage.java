package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ChangeBirthDatePage extends BasePage {

    @FindBy(id = "id_new_birthDate")
    private WebElement birthDateField;

    @FindBy(xpath = "//button[contains(@class,'btn-change-birthDate')]")
    private WebElement changeBirthDateButton;

    @FindBy(xpath = "//div[contains(@class,'react-datepicker__day--selected')]")
    private WebElement selectedDate;

    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(birthDateField));
    }

    @Step("Enter new birthdate")
    public void enterBirthDateField(String birthDate) {
        inputText(birthDateField, birthDate);
    }

    @Step("Click the button to save the new birthdate and return to the profile page")
    public ProfilePage clickChangeBirthDateButton() {
        changeBirthDateButton.click();
        return new ProfilePage();
    }

    @Step("Close the date picker")
    public void closeDatePicker() {
        selectedDate.click();
    }
}
