package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ChangePasswordPage extends BasePage{

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
    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(currentPasswordField));
    }


}
