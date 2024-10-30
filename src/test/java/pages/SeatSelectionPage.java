package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeatSelectionPage extends BasePage {

    @FindBy(xpath = "//div[@class='col-xs-6 col-sm-5 col-md-4']//button[contains(@class,'button_schema_submit')]")
    private WebElement seatSelectionContinueButton;

    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(seatSelectionContinueButton));
    }

    @Step("Click on the continue button in the seat selection page")
    public TicketPreviewPage clickContinueButton() {
        seatSelectionContinueButton.click();
        return new TicketPreviewPage();
    }


}
