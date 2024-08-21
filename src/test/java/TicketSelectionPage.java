import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TicketSelectionPage extends BasePage {

    @FindBy(xpath = "//input[contains(@class,'form-control passengers-names')]")
    private WebElement nameField;

    @FindBy(xpath = "//div[@class = 'col-sm-4']//select")
    private WebElement genderField;

    @FindBy(xpath = "//div[contains(@class,'react-datepicker')]//input")
    private WebElement dateField;

    @FindBy(xpath = "//div[@class = 'col-sm-7']//select")
    private WebElement discountField;

    @FindBy(xpath = "//div[@class='col-xs-6 col-sm-5 col-md-4']//button[@id='btn-ticket-next']")
    private WebElement ticketSelectContinueButton;


    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(nameField));
    }

    public String getNameFieldText() {
        return nameField.getAttribute("value");
    }

    public String getGenderFieldText() {
        return genderField.getAttribute("value");
    }

    public String getDateFieldText() {
        return dateField.getAttribute("value");
    }

    public String getDiscountFieldText() {
        return discountField.getText();
    }

    public SeatSelectionPage clickContinueButton() {
        ticketSelectContinueButton.click();
        return new SeatSelectionPage();
    }
}
