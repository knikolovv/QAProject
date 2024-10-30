package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TicketSelectionPage extends BasePage {

    @FindBy(xpath = "//input[contains(@class,'form-control passengers-names')]")
    private WebElement nameField;

    @FindBy(xpath = "//div[@class='col-xs-6 col-sm-5 col-md-4']//button[@id='btn-ticket-next']")
    private WebElement ticketSelectContinueButton;

    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(nameField));
    }

    public SeatSelectionPage clickContinueButton() {
        ticketSelectContinueButton.click();
        return new SeatSelectionPage();
    }

}
