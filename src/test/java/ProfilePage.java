import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {

    @FindBy(id = "profileChangePhone")
    private WebElement profileChangePhoneButton;
    @FindBy(xpath = "//div[@class='col-md-7']//input")
    private WebElement changePhoneNumberField;

    @FindBy(id = "id_new_birthDate")
    private WebElement birthDateField;

    @FindBy(className = "col-xs-4")
    private WebElement profileButtonsSection;

    public void enterPhoneNumber(String number) {
        inputText(changePhoneNumberField,number);
    }

    public void enterBirthDateField(String birthDate) {
        inputText(birthDateField,birthDate);
    }

    public WebElement profileButtonsSection() {
        return profileButtonsSection;
    }

    public WebElement profileChangePhoneButton() {
        return profileChangePhoneButton;
    }
}
