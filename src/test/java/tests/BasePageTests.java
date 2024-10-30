package tests;

import pages.SearchPage;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BasePageTests extends BaseTest {

    @Test
    @DisplayName("WrapperPage components test")
    @Description("Tests the header buttons on the wrapper page")
    public void headerButtonsTest() {

        SearchPage searchPage = SearchPage.open();

        searchPage.clickLoginHeaderButton();
        searchPage.clickRegisterHeaderButton();
        // When loading the page it bugs and shows wrong flag with the wrong language, so you have to double-click

        searchPage.clickLogo();
        searchPage.changeLanguage();

    }
}
