import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.format.DateTimeFormatter;

public class BasePageTests extends BasePage {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Test
    public void headerButtonsTest() throws InterruptedException {

        loginControlsButton.click();
        Thread.sleep(1000);
        registerControlsButton.click();
        Thread.sleep(1000);
        // When loading the page it bugs and shows wrong flag with the wrong language, so you have to double click
        langControlsButton.click();
        langControlsButton.click();
        Thread.sleep(1000);
        logo.click();
        Thread.sleep(1000);
        langControlsButton.click();

    }

    @Test
    public void buyingTicketTest() {

        // Step - Select stations
        String exampleStation = "София";
        startStationButton.click();
        startStationInput.sendKeys(exampleStation);
        driver.findElement(By.xpath("//*[contains(@class, 'option_custom')]")).click();

        String exampleStation2 = "Бургас";
        endStationButton.click();
        endStationInput.sendKeys(exampleStation2);
        driver.findElement(By.xpath("//*[contains(@class, 'option_custom')]")).click();

        // Step - Select going date,check it if it's correct and search
        currentDateButton.click();
        currentDateButton.sendKeys(Keys.CONTROL + "a");
        currentDateButton.sendKeys(Keys.DELETE);
        currentDateButton.sendKeys(date.plusDays(1).format(formatter));
        verifyText("Date", currentDateButton.getAttribute("value"), date.plusDays(1).format(formatter));
        searchButton.click();

        // Step - Select train and continue
        wait.until(driver -> driver.findElement(By.xpath("//li[@class = 'list-group-item']")).isDisplayed());
        driver.findElement(By.xpath("//li[@class = 'list-group-item']")).click();

        driver.findElement(By.xpath("//div[@class='col-xs-6  col-sm-5 col-md-4']//button[@type='submit']")).click();

        // Step - Login with correct data
        login(p.getProperty("email"), p.getProperty("password"));

        //Step - Verify passenger info
        String expectedName = "test test";
        wait.until(driver -> nameField.isDisplayed());
        String actualName = nameField.getAttribute("value");
        verifyText("Name",expectedName,actualName);

        String expectedGender = "m";
        verifyText("Gender",expectedGender,genderField.getAttribute("value"));

        String expectedDate = "01.02.1998";
        verifyText("Date", expectedDate, dateField.getAttribute("value"));

        //Todo verify no discount document is selected

        //Todo continue buying ticket
    }


}
