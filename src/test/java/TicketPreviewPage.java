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


    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(paymentMethodRadioButton));
    }

    public void clickPaymentMethodButton() {
        paymentMethodRadioButton.click();
    }

    public void clickTermsAndConditionsButton() {
        termsAndConditionsCheckbox.click();
    }

    public void clickContinueButton() {
        continueToPaymentButton.click();
    }

    public void verifyPaymentPageIsOpened() {
        wait.until(ExpectedConditions.urlToBe("https://3dsgate.borica.bg/cgi-bin/cgi_link"));
    }
}