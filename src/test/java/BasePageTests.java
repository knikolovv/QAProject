import org.junit.jupiter.api.Test;

public class BasePageTests extends BasePage {

    @Test
    public void headerButtonsTest() {

        clickElement(loginHeaderButton);
        clickElement(registerHeaderButton);
        // When loading the page it bugs and shows wrong flag with the wrong language, so you have to double-click
        changeLanguage();
        changeLanguage();
        clickElement(logo);
        clickElement(langHeaderButton);

    }
}
