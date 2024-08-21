import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.format.DateTimeFormatter;

public class SearchPage extends BasePage {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
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

    @FindBy(xpath = "//li[@class = 'list-group-item']")
    private WebElement listOfRides;

    @FindBy(xpath = "//div[@class='col-xs-6  col-sm-5 col-md-4']//button[@type='submit']")
    private WebElement continueButton;



    @FindBy(xpath = "//div[@id='header-controls']//a[@href='/login']")
    private WebElement loginHeaderButton;

    @FindBy(xpath = "//div[@id='header-controls']//a[@href='/register']")
    private WebElement registerHeaderButton;

    @FindBy(id = "header-logo")
    private WebElement logo;

    public static SearchPage open() {
        driver.get(Props.getUrl());
        return new SearchPage();
    }

    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(departureDateField));
    }

    public LoginPage clickLoginHeaderButton() {
        loginHeaderButton.click();
        return new LoginPage();
    }

    public void enterStartStation(String startStation) {
        // TODO MAYBE IF FAILS - startStationButton.click();
        //startStationInput.click();
        inputText(startStationInput, startStation);
    }

    public void enterEndStation(String endStation) {
        // endStationButton.click();
        //endStationInput.click();
        inputText(endStationInput, endStation);
    }

    public void enterDepartureDate(String departureDate) {
        // if doesnt work try .click()
        inputText(departureDateField, departureDate);
    }

    public void selectStationFromDropdownByIndex(int index) {
        String STATION_DROPDOWN_OPTIONS = "//*[contains(@class, 'option_custom')]";
        driver.findElement(By.xpath(String.format("%s[%d]", STATION_DROPDOWN_OPTIONS, index))).click();
    }

    public void selectRideFromListByIndex(int index) {
        String xpathExpression = String.format("%s[%d]", "//li[@class = 'list-group-item']", index);
        driver.findElement(By.xpath(xpathExpression)).click();
    }

    public void doSearch(String departureStation, String endStation, String departureDate) {
        //TODO ADD implementation
    }

    public void clickRegisterHeaderButton() {
        registerHeaderButton.click();
    }

    public void clickLogo() {
        logo.click();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void verifyDepartureDate() {
        verifyText("Date", departureDateField.getAttribute("value"), BaseTest.date.plusDays(1).format(formatter));
    }

    public void verifyListOfRidesIsVisible() {
        wait.until(ExpectedConditions.visibilityOf(listOfRides));
    }

    public LoginPage clickContinueButton() {
        continueButton.click();
        return new LoginPage();
    }


}
