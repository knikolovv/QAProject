import org.junit.jupiter.api.Test;

//TODO ASK vsqka stranica vrushta druga kato tursq, kak trqbva da kombiniram clasovete i testovete ?
public class SearchPageTest extends BaseTest {

    @Test
    public void buyingTicketTest() {
        SearchPage searchPage = SearchPage.open();
        // Step - Select stations
        String exampleStation = "София";
        searchPage.enterStartStation(exampleStation);
        searchPage.selectStationFromDropdownByIndex(1);

        String exampleStation2 = "Пловдив";
        searchPage.enterEndStation(exampleStation2);
        searchPage.selectStationFromDropdownByIndex(2);

        // Step - Select going date,check it if it's correct and search
//        clickElement(departureDateField());
        searchPage.enterDepartureDate(date.plusDays(1).format(SearchPage.formatter));

        searchPage.verifyDepartureDate();
        searchPage.clickSearchButton();

        // Step - Select train and continue

        searchPage.verifyListOfRidesIsVisible();
        searchPage.selectRideFromListByIndex(1);

        // TODO - Correct train check - ting wrong text
//        String trainId = driver.findElement(By.xpath(
//                "//div[contains(text(),'Влак')]//ancestor::div[@class='row']//div[contains(@class,'text-ellipsis')]//div//strong"))
//                .Text();
//        System.out.println(trainId);

        LoginPage loginPage = searchPage.clickContinueButton();

        // Step - Login with correct data
        TicketSelectionPage ticketSelectionPage = loginPage.loginToTicketSelectionPage(Props.getEmail(), Props.getPassword());

        //Step - Verify passenger info
        ticketSelectionPage.verifyPageIsOpen();
        String currentName = ticketSelectionPage.getNameFieldText();
        ticketSelectionPage.verifyText("Name", Props.getName(), currentName);

        String gender = ticketSelectionPage.getGenderFieldText();
        ticketSelectionPage.verifyText("Gender", Props.getGender(), gender);

        String birthDate = ticketSelectionPage.getDateFieldText();
        ticketSelectionPage.verifyText("Date", Props.getBirthDate(), birthDate);

        String discount = ticketSelectionPage.getDiscountFieldText();
        ticketSelectionPage.verifyText("Discount", Props.getDiscount(), discount);

        SeatSelectionPage seatSelectionPage = ticketSelectionPage.clickContinueButton();

        // Step - choose seat
        seatSelectionPage.verifyPageIsOpen();
        TicketPreviewPage ticketPreviewPage = seatSelectionPage.clickContinueButton();

        // step - choose payment and continue
        ticketPreviewPage.verifyPageIsOpen();
        ticketPreviewPage.clickPaymentMethodButton();
        ticketPreviewPage.clickTermsAndConditionsButton();
        ticketPreviewPage.clickContinueButton();

        ticketPreviewPage.verifyPaymentPageIsOpened();
    }


}
