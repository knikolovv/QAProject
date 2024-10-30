package tests;

import pages.*;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import utils.Props;

public class SearchPageTests extends BaseTest {

    @Test
    @DisplayName("Simple buying ticket test")
    @Description("Test selecting stations, departure date, selecting a ride, selecting no particular seat, selecting payment methods" +
                 " and verifying the trainId is as expected")
    public void simpleBuyingTicketTest() {
        SearchPage searchPage = SearchPage.open();

        searchPage.doSearch("София","Пловдив",date.plusDays(3).format(SearchPage.formatter));

        searchPage.verifyDepartureDate();
        searchPage.clickSearchButton();

        // Step - Select train and continue
        searchPage.verifyListOfRidesIsVisible();
        searchPage.selectRideFromListByIndex(3);

        String trainId = searchPage.getTrainId();

        LoginPage loginPage = searchPage.clickContinueButton();

        TicketSelectionPage ticketSelectionPage = loginPage.loginToTicketSelectionPage(Props.getEmail(), Props.getPassword());

        ticketSelectionPage.verifyPageIsOpen();

        // Step - choose no particular seat
        SeatSelectionPage seatSelectionPage = ticketSelectionPage.clickContinueButton();
        seatSelectionPage.verifyPageIsOpen();

        // step - choose payment and continue
        TicketPreviewPage ticketPreviewPage = seatSelectionPage.clickContinueButton();
        ticketPreviewPage.verifyPageIsOpen();
        ticketPreviewPage.verifyTrainId(trainId);
        ticketPreviewPage.clickPaymentMethodButton();
        ticketPreviewPage.clickTermsAndConditionsButton();
        ticketPreviewPage.clickContinueButton();

        ticketPreviewPage.verifyPaymentPageIsOpened();
    }

//    @Test
//    @DisplayName("Extended buying ticket test")
//    @Description("Test selecting stations,departure and return date, selecting a ride, selecting a seat," +
//                 "checking payment methods and terms and conditions error messages when not selected, " +
//                 "selecting payment method and verifying trainId and seat number")
//    public void extendedBuyingTicketTest() {
//        SearchPage searchPage = SearchPage.open();
//    }


}
