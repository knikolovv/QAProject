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
    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(profileButtonsSection));
    }

    public static ProfilePage open(String email, String password) {
        LoginPage loginPage = LoginPage.open();
        loginPage.login(email, password);
        return loginPage.openProfile();
    }

    public ChangePhonePage clickChangePhoneButton() {
        profileChangePhoneButton.click();
        return new ChangePhonePage();
    }

    public void verifyPhoneNumberIsChanged(String changedNumber) {
        verifyText("Phone Number",changedNumber,phoneNumber.getText());
    }

    public ChangeGenderPage clickChangeGenderButton() {
        profileChangeGenderButton.click();
        return new ChangeGenderPage();
    }

    public void verifyGenderIsChanged(String changedGender) {
        verifyText("Gender",changedGender,gender.getText());
    }

    public ChangeBirthDatePage clickChangeBirthDateButton() {
        profileChangeBirthDateButton.click();
        return new ChangeBirthDatePage();
    }


    public void verifyBirthDateIsChanged(String changedBirthDate) {
        verifyText("BirthDate",changedBirthDate,birthDate.getText());
    }
}
