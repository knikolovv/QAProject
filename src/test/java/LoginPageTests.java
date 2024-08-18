import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;

public class LoginPageTests extends LoginPage {
    public static Random random = new Random();

    @Test
    public void loginTest() {

        loginHeaderButton.click();

        // Step - Login with incorrect data
        login("wrong1@abv.bg","Wr0ngPass");

        // Step - Login with correct data
        login(p.getProperty("email"), p.getProperty("password"));
        wait.until(ExpectedConditions.elementToBeClickable(profileButton));
        profileButton.click();

        logo.click();

        //Zabravih kak se tursi xpath chrez drug element, toest da se vurna nazad i da tursi ot tam
        logout();
    }

}