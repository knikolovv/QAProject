package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TicketPreviewPage extends BasePage {
    @FindBy(xpath = "//label[@class='radio']//input[@id='id_payment_provider_1']")
    private WebElement paymentMethodRadioButton;

    @FindBy(xpath = "//label[@class='checkbox']//input")
    private WebElement termsAndConditionsCheckbox;

    @FindBy(xpath = "//div[@class='col-xs-6  col-sm-5 col-md-4']//button[@id='btn-wizard-next']")
    private WebElement continueToPaymentButton;

    @FindBy(xpath = "//div[@class='row text-small']//div[@class='col-sm-10 col-sm-offset-1'][2]")
    private WebElement ticketInformation;


    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(paymentMethodRadioButton));
    }

    @Step("Selects the first payment option")
    public void clickPaymentMethodButton() {
        paymentMethodRadioButton.click();
    }

    @Step("Check the terms and conditions checkbox")
    public void clickTermsAndConditionsButton() {
        termsAndConditionsCheckbox.click();
    }

    @Step("Click on the continue button in the ticket preview page, which sends him to the payment page")
    public void clickContinueButton() {
        continueToPaymentButton.click();
    }

    @Step("Verifies the train ID is as expected")
    public void verifyTrainId(String trainId) {
        assert ticketInformation.getText().contains(trainId.replace(" ", ""));
    }

    @Step("Verify payment page opened successfully")
    public void verifyPaymentPageIsOpened() {
        wait.until(ExpectedConditions.urlToBe("https://3dsgate.borica.bg/cgi-bin/cgi_link"));
    }
}