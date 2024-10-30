package pages;

import io.qameta.allure.Step;
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

    @Step("Open the dropdown menu to choose gender")
    public void openChangeGenderDropdown() {
        changeGenderField.click();
    }

    @Step("Select a new gender")
    public void selectGenderByIndex(int index) {
        String genderDropdownOptions = String.format("%s[%d]","//select[@id='id_new_gender']//option",index);
        driver.findElement(By.xpath(genderDropdownOptions)).click();
    }

    @Step("Click the button to save the changes and return to the profile page")
    public ProfilePage clickChangeGenderButton() {
        changeGenderButton.click();
        return new ProfilePage();
    }
}
