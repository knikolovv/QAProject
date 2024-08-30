import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ChangeGenderPage extends BasePage{
    @FindBy(id = "id_new_gender")
    private WebElement changeGenderField;

    @FindBy(xpath = "//button[contains(@class,'btn-change-gender')]")
    private WebElement changeGenderButton;

    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(changeGenderField));
    }

    public void openChangeGenderDropdown() {
        changeGenderField.click();
    }

    public void selectGenderByIndex(int index) {
        String genderDropdownOptions = String.format("%s[%d]","//select[@id='id_new_gender']//option",index);
        driver.findElement(By.xpath(genderDropdownOptions)).click();
    }

    public ProfilePage clickChangeGenderButton() {
        changeGenderButton.click();
        return new ProfilePage();
    }
}
