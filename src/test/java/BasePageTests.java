import org.junit.jupiter.api.Test;

public class BasePageTests extends BaseTest {

    @Test
    public void headerButtonsTest() {

        SearchPage searchPage = SearchPage.open();

        searchPage.clickLoginHeaderButton();
        searchPage.clickRegisterHeaderButton();
        // When loading the page it bugs and shows wrong flag with the wrong language, so you have to double-click

        searchPage.clickLogo();
        searchPage.changeLanguage();

    }
}
