package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {
    @FindBy(id = "profileChangePhone")
    private WebElement profileChangePhoneButton;

    @FindBy(className = "col-xs-4")
    private WebElement profileButtonsSection;

    @FindBy(xpath = "//div[contains(@class,'profile-row')][5]//div[contains(@class,'text-ellipsis')]")
    private WebElement phoneNumber;

    @FindBy(id = "profileChangeGender")
    private WebElement profileChangeGenderButton;

    @FindBy(xpath = "//div[contains(@class,'profile-row')][3]//div[contains(@class,'text-ellipsis')]")
    private WebElement gender;

    @FindBy(xpath = "//div[contains(@class,'profile-row')][4]//div[contains(@class,'text-ellipsis')]")
    private WebElement birthDate;

    @FindBy(id = "profileChangeBirthDate")
    private WebElement profileChangeBirthDateButton;

    @FindBy(xpath = "//div[@class='col-xs-4']//a[contains(@href,'/profile-tickets')]")
    private WebElement ticketsButton;

    @FindBy(id = "profileChangePassword")
    private WebElement changePasswordButton;

    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(profileButtonsSection));
    }

    @Step("Opens the user profile page")
    public static ProfilePage open(String email, String password) {
        LoginPage loginPage = LoginPage.open();
        loginPage.login(email, password);
        return loginPage.openProfile();
    }

    @Step("Click the change phone button, which sends the user to the change phone page")
    public ChangePhonePage clickChangePhoneButton() {
        profileChangePhoneButton.click();
        return new ChangePhonePage();
    }

    @Step("Verifies the phone number is as expected")
    public void verifyPhoneNumberIsChanged(String changedNumber) {
        verifyText("Phone Number",changedNumber,phoneNumber.getText());
    }

    @Step("Click the change gender button, which sends the user to the change gender page")
    public ChangeGenderPage clickChangeGenderButton() {
        profileChangeGenderButton.click();
        return new ChangeGenderPage();
    }

    @Step("Verifies the gender is changed successfully")
    public void verifyGenderIsChanged(String changedGender) {
        verifyText("Gender",changedGender,gender.getText());
    }

    @Step("Click the change birth date button, which sends the user to the change birthdate page ")
    public ChangeBirthDatePage clickChangeBirthDateButton() {
        profileChangeBirthDateButton.click();
        return new ChangeBirthDatePage();
    }

    @Step("Verifies the birthdate is changed")
    public void verifyBirthDateIsChanged(String changedBirthDate) {
        verifyText("BirthDate",changedBirthDate,birthDate.getText());
    }

    public String getCurrentGender() {
        return gender.getText();
    }

    public ProfileTicketsPage clickTicketsButton() {
        ticketsButton.click();
        return new ProfileTicketsPage();
    }
}
