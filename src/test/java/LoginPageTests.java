import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPageTests extends BasePage {
    public LoginPageTests() {
        super();
    }

    @Test
    public void loginTest() {

        loginControlsButton.click();

        // Step - Login with incorrect data
        login("wrong1@abv.bg","Wr0ngPass");

        // Step - Login with correct data
        login(p.getProperty("email"), p.getProperty("password"));
        wait.until(ExpectedConditions.elementToBeClickable(profileButton));
        profileButton.click();

        logo.click();

        //Zabravih kak se tursi xpath chrez drug element, toest da se vurna nazad i da tursi ot tam
        driver.findElement(By.xpath("//div[@id='header-controls']//button[@class='control-exit-profile']")).click();
    }

}