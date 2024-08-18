import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
//TODO make every element private
    @FindBy(id = "id-search-trips-station-from")
    private WebElement startStationButton;

    @FindBy(id = "react-select-2-input")
    private WebElement startStationInput;

    @FindBy(className = "css-yk16xz-control")
    private WebElement endStationButton;

    @FindBy(id = "react-select-3-input")
    private WebElement endStationInput;

    @FindBy(id = "id-search-trips-going-date")
    private WebElement departureDateField;

    @FindBy(xpath = "//div[@class='col-md-2']//button")
    private WebElement searchButton;

    @FindBy(xpath = "//input[contains(@class,'form-control passengers-names')]")
    private WebElement nameField;

    @FindBy(xpath = "//div[@class = 'col-sm-4']//select")
    private WebElement genderField;

    @FindBy(xpath = "//div[contains(@class,'react-datepicker')]//input")
    private WebElement dateField;

    @FindBy(xpath = "//li[@class = 'list-group-item']")
    private WebElement listOfRides;

    @FindBy(xpath = "//div[@class='col-xs-6  col-sm-5 col-md-4']//button[@type='submit']")
    private WebElement continueButton;

    @FindBy(xpath = "//div[@class='col-xs-6 col-sm-5 col-md-4']//button[@id='btn-ticket-next']")
    private WebElement ticketSelectContinueButton;

    @FindBy(xpath = "//div[@class='col-xs-6 col-sm-5 col-md-4']//button")
    private WebElement ticketSeatsContinueButton;

    @FindBy(xpath = "//label[@class='radio']//input[@id='id_payment_provider_1']")
    private WebElement paymentMethodRadioButton;

    @FindBy(xpath = "//label[@class='checkbox']//input")
    private WebElement termsAndConditionsCheckbox;

    @FindBy(xpath = "//div[@class='col-xs-6  col-sm-5 col-md-4']//button[@id='btn-wizard-next']")
    private WebElement continueToPaymentButton;
    private final String STATION_DROPDOWN_OPTIONS = "//*[contains(@class, 'option_custom')]";

    public WebElement startStationButton() {
        return startStationButton;
    }

    public WebElement endStationButton() {
        return endStationButton;
    }

    public WebElement departureDateField() {
        return departureDateField;
    }

    public WebElement searchButton() {
        return searchButton;
    }

    public WebElement nameField() {
        return nameField;
    }

    public WebElement genderField() {
        return genderField;
    }

    public WebElement dateField() {
        return dateField;
    }

    public WebElement listOfRides() {
        return listOfRides;
    }

    public WebElement continueButton() {
        return continueButton;
    }

    public WebElement ticketSelectContinueButton() {
        return ticketSelectContinueButton;
    }

    public WebElement ticketSeatsContinueButton() {
        return ticketSeatsContinueButton;
    }

    public WebElement paymentMethodRadioButton() {
        return paymentMethodRadioButton;
    }

    public WebElement termsAndConditionsCheckbox() {
        return termsAndConditionsCheckbox;
    }

    public WebElement continueToPaymentButton() {
        return continueToPaymentButton;
    }



    public void enterStartStation(String startStation) {
        inputText(startStationInput,startStation);
    }

    public void enterEndStation(String endStation) {
        inputText(endStationInput,endStation);
    }

    public void enterDepartureDate(String departureDate) {
        inputText(departureDateField,departureDate);
    }

    public void selectStationFromDropdownByIndex(int index) {
        driver.findElement(By.xpath(String.format("%s[%d]", STATION_DROPDOWN_OPTIONS,index))).click();
    }

    // TODO ASK moga li da izpolzvam direktno webelementa listofrides vmesto
    public void selectRideFromListByIndex(int index) {
        String xpathExpression = String.format("%s[%d]", "//li[@class = 'list-group-item']", index);
        driver.findElement(By.xpath(xpathExpression)).click();
    }

}
