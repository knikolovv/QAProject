import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.format.DateTimeFormatter;

//TODO ASK vsqka stranica vrushta druga kato tursq, kak trqbva da kombiniram clasovete i testovete ?
public class SearchPageTest extends SearchPage {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @Test
    public void buyingTicketTest() {

        // Step - Select stations
        String exampleStation = "София";
        clickElement(startStationButton());
        enterStartStation(exampleStation);
        selectStationFromDropdownByIndex(1);

        String exampleStation2 = "Пловдив";
        clickElement(endStationButton());
        enterEndStation(exampleStation2);
        selectStationFromDropdownByIndex(2);

        // Step - Select going date,check it if it's correct and search
        clickElement(departureDateField());
        enterDepartureDate(date.plusDays(1).format(formatter));

        verifyText("Date", departureDateField().getAttribute("value"), date.plusDays(1).format(formatter));
        clickElement(searchButton());

        // Step - Select train and continue

        wait.until(ExpectedConditions.visibilityOf(listOfRides()));
        selectRideFromListByIndex(1);

        // TODO - Correct train check - ting wrong text
//        String trainId = driver.findElement(By.xpath(
//                "//div[contains(text(),'Влак')]//ancestor::div[@class='row']//div[contains(@class,'text-ellipsis')]//div//strong"))
//                .Text();
//        System.out.println(trainId);

        clickElement(continueButton());

        // Step - Login with correct data
        login(p.getProperty("email"), p.getProperty("password"));

        //Step - Verify passenger info
        wait.until(driver -> nameField().isDisplayed());
        String currentName = nameField().getAttribute("value");
        verifyText("Name",p.getProperty("name"),currentName);

        String gender = genderField().getAttribute("value");
        verifyText("Gender",p.getProperty("gender"),gender);

        String birthDate = dateField().getAttribute("value");
        verifyText("Date", p.getProperty("birthDate"), birthDate);

        String discount = "Без намаление";
        verifyText("Discount",p.getProperty("discount"), discount);

        clickElement(ticketSelectContinueButton());

        // Step - choose seat
        wait.until(ExpectedConditions.urlToBe("https://bileti.bdz.bg/ticket-seats"));
        clickElement(ticketSeatsContinueButton());

        // step - choose payment and continue
        wait.until(ExpectedConditions.urlToBe("https://bileti.bdz.bg/ticket-preview"));
        clickElement(paymentMethodRadioButton());
        clickElement(termsAndConditionsCheckbox());
        clickElement(continueToPaymentButton());

        wait.until(ExpectedConditions.titleIs("Borica E-Payment"));
        verifyText("Payment link", "https://3dsgate.borica.bg/cgi-bin/cgi_link", driver.getCurrentUrl());
    }


}
