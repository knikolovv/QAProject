package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangePasswordPage extends BasePage {

    @FindBy(id = "currentPassword")
    private WebElement currentPasswordField;

    @FindBy(id = "newPassword")
    private WebElement newPasswordField;

    @FindBy(id = "repeatPassword")
    private WebElement repeatNewPasswordField;

    @FindBy(xpath = "//button[contains(@class,'btn-custom-change-password')]")
    private WebElement changePasswordButton;

    @FindBy(xpath = "//a[contains(@class,'cancelProfile')]")
    private WebElement cancelButton;

    @FindBy(xpath = "//div[contains(@class,'form-group  ')]//div[contains(@class,'col-md-7')]//p")
    private WebElement currentPasswordErrorMessage;

    @FindBy(xpath = "//label[@for='newPassword']/ancestor::div[contains(@class, 'form-group')]" +
                    "//p[contains(@class,'text-danger denied-form')]")
    private WebElement newPasswordFieldErrorMessage;

    @FindBy(xpath = "//label[@for='repeatPassword']/ancestor::div[contains(@class, 'form-group')]" +
                    "//p[contains(@class,'text-danger denied-form')]")
    private WebElement repeatPasswordFieldErrorMessage;

    @FindBy(xpath = "//div[contains(@class,'alert alert-danger text-center')]")
    private WebElement errorMessageArea;

    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(currentPasswordField));
    }


    public void clickChangePasswordButtonWithWronglyPopulatedFields() {
        changePasswordButton.click();
    }

    public LoginPage clickChangePasswordButtonWithAllPasswordsCorrect() {
        changePasswordButton.click();
        return new LoginPage();
    }

    public void verifyBaseErrorMessagesAppear() {
        verifyFieldIsMandatoryErrorMessage();
        verifyPasswordsMustBeDifferentErrorMessage();
    }

    public void enterSameNewAndOldPassword() {
        inputText(currentPasswordField, "sameP@ss");
        inputText(newPasswordField, "sameP@ss");
    }

    public void verifyFieldIsMandatoryErrorMessage() {
        assertThat(currentPasswordErrorMessage.getText()).isEqualTo("Полето е задължително");
    }


    public void verifyPasswordsMustBeDifferentErrorMessage() {
        assertThat(newPasswordFieldErrorMessage.getText()).isEqualTo("Новата и старата парола трябва да са различни");
    }

    public void enterWrongCurrentPassword() {
        inputText(currentPasswordField, "wrongPass1");
    }

    public void verifyWrongCurrentPasswordErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessageArea));
        assertThat(errorMessageArea.getText()).isEqualTo("Грешна парола");
    }

    public void verifyPasswordMustMatchErrorMessage() {
        assertThat(repeatPasswordFieldErrorMessage.getText()).isEqualTo("Паролите трябва да съвпадат");
    }

    public void enterDifferentNewAndRepeatedPassword() {
        inputText(newPasswordField, "NewPassword!");
        inputText(repeatNewPasswordField, "RepeatedPassword!");
    }

    public void enterSameNewAndRepeatPassword() {
        inputText(newPasswordField, "NewPassword!");
        inputText(repeatNewPasswordField, "NewPassword!");
    }

    public void enterAllValidPassword(String currentPassword, String newPassword) {
        inputText(currentPasswordField,currentPassword);
        inputText(newPasswordField, newPassword);
        inputText(repeatNewPasswordField, newPassword);
    }

    public ProfilePage clickCancelButton() {
        cancelButton.click();
        return new ProfilePage();
    }

//    public void verifyPasswordHintsAppear() {
//        assertThat(errorMessageArea.getText()).isEqualTo("Грешна парола");
//    }
}
