package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import tests.BaseTest;
import utils.Props;

import java.time.format.DateTimeFormatter;

public class SearchPage extends BasePage {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @FindBy(id = "react-select-2-input")
    private WebElement startStationInput;

    @FindBy(id = "react-select-3-input")
    private WebElement arrivalStationInput;

    @FindBy(id = "id-search-trips-going-date")
    private WebElement departureDateField;

    @FindBy(xpath = "//div[@class='col-md-2']//button")
    private WebElement searchButton;

    @FindBy(xpath = "//li[@class = 'list-group-item']")
    private WebElement listOfRides;

    @FindBy(xpath = "//div[@class='col-xs-6  col-sm-5 col-md-4']//button[@type='submit']")
    private WebElement continueButton;

    @FindBy(xpath = "//li[@class='list-group-item active']//ul[@class='routes']//li[@class='route']//div[@class='row'][2]//" +
                    "div[contains(@class,'col-xs-10 text-ellipsis')]//div[contains(@style,'display: inline; white-space: nowrap;')]//strong")
    private WebElement trainId;


    @Step("Open the search page")
    public static SearchPage open() {
        driver.get(Props.getUrl());
        return new SearchPage();
    }

    @Override
    public void verifyPageIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(departureDateField));
    }

    @Step("Enter departure station")
    public void enterDepartureStation(String departureStation) {
        inputText(startStationInput, departureStation);
    }

    @Step("Enter arrival station")
    public void enterArrivalStation(String arrivalStation) {
        inputText(arrivalStationInput, arrivalStation);
    }

    @Step("Enter departure date")
    public void enterDepartureDate(String departureDate) {
        inputText(departureDateField, departureDate);
    }

    @Step("Selects a station from a dropdown list")
    public void selectStationFromDropdownByIndex(int index) {
        String stationDropdownOptions = String.format("%s[%d]", "//*[contains(@class, 'option_custom')]", index);
        driver.findElement(By.xpath(stationDropdownOptions)).click();
    }

    @Step("Selects a train")
    public void selectRideFromListByIndex(int index) {
        String ridesDropdownOptions = String.format("%s[%d]", "//li[@class = 'list-group-item']", index);
        driver.findElement(By.xpath(ridesDropdownOptions)).click();
    }

    @Step("Select departure station,arrival station and departure date")
    public void doSearch(String departureStation, String arrivalStation, String departureDate) {
        // Select stations
        enterDepartureStation(departureStation);
        selectStationFromDropdownByIndex(1);

        enterArrivalStation(arrivalStation);
        selectStationFromDropdownByIndex(2);

        // Step - Select going date,check it if it's correct and search
        enterDepartureDate(departureDate);
        verifyDepartureDate();
    }

    @Step("Click search button")
    public void clickSearchButton() {
        searchButton.click();
    }

    @Step("Verify departure date")
    public void verifyDepartureDate() {
        verifyText("Date", departureDateField.getAttribute("value"), BaseTest.date.plusDays(3).format(formatter));
    }

    @Step("Verify list of rides is visible")
    public void verifyListOfRidesIsVisible() {
        wait.until(ExpectedConditions.visibilityOf(listOfRides));
    }

    @Step("Click on continue button on the search page, which sends the user to the logging page if he is not logged in")
    public LoginPage clickContinueButton() {
        continueButton.click();
        return new LoginPage();
    }

    public String getTrainId() {
        wait.until(ExpectedConditions.visibilityOf(trainId));
        return trainId.getText();
    }

}
