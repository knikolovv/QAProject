import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.format.DateTimeFormatter;

public class BasePageTests extends BasePage {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Test
    public void headerButtonsTest() throws InterruptedException {

        loginControlsButton.click();
        registerControlsButton.click();
        // When loading the page it bugs and shows wrong flag with the wrong language, so you have to double click
        langControlsButton.click();
        langControlsButton.click();
        logo.click();
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

        // TODO - Correct train check - Getting wrong text
//        String trainId = driver.findElement(By.xpath("//span[@class='bdz-icon-1class']/div//strong")).getText();
//        System.out.println(trainId);

        driver.findElement(By.xpath("//div[@class='col-xs-6  col-sm-5 col-md-4']//button[@type='submit']")).click();

        // Step - Login with correct data
        login(p.getProperty("email"), p.getProperty("password"));

        //Step - Verify passenger info
        wait.until(driver -> nameField.isDisplayed());
        String currentName = nameField.getAttribute("value");
        verifyText("Name",p.getProperty("name"),currentName);

        String gender = genderField.getAttribute("value");
        verifyText("Gender",p.getProperty("gender"),gender);

        String birthDate = dateField.getAttribute("value");
        verifyText("Date", p.getProperty("birthDate"), birthDate);

        String discount = "Без намаление";
        verifyText("Discount",p.getProperty("discount"), discount);

        driver.findElement(By.xpath("//div[@class='col-xs-6 col-sm-5 col-md-4']//button[@id='btn-ticket-next']")).click();

        // Step - choose seat
        wait.until(ExpectedConditions.urlToBe("https://bileti.bdz.bg/ticket-seats"));
        driver.findElement(By.xpath("//div[@class='col-xs-6 col-sm-5 col-md-4']//button")).click();

        // step - choose payment and continue
        wait.until(ExpectedConditions.urlToBe("https://bileti.bdz.bg/ticket-preview"));
        driver.findElement(By.xpath("//label[@class='radio']//input[@id='id_payment_provider_1']")).click();
        driver.findElement(By.xpath("//label[@class='checkbox']//input")).click();
        driver.findElement(By.xpath("//div[@class='col-xs-6  col-sm-5 col-md-4']//button")).click();

        wait.until(ExpectedConditions.titleIs("Borica E-Payment"));
        verifyText("Payment link", "https://3dsgate.borica.bg/cgi-bin/cgi_link", driver.getCurrentUrl());
    }


}
