import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;

public class ProfilePageTests extends ProfilePage {
    //TODO finish the test
    public static Random random = new Random();
    @Test
    public void changingProfileInfoTest() throws InterruptedException {
        // Step - Sign in profile
        clickElement(loginHeaderButton);
        //login(p.getProperty("email"),p.getProperty("password"));

        wait.until(ExpectedConditions.elementToBeClickable(profileButton));
        clickElement(profileButton);


        //Step - Change phone number
        String randomPhoneNumber = "08" + random.nextInt(10000000,99999999);
        clickElement(profileChangePhoneButton());

        enterPhoneNumber(randomPhoneNumber);
        driver.findElement(By.xpath("//div[contains(@class,'col-md-offset-5')]//button")).click();
        wait.until(ExpectedConditions.visibilityOf(profileButtonsSection()));
        verifyText("Phone number", randomPhoneNumber,driver.findElement
                (By.xpath("//div[contains(@class,'profile-row')][5]//div[contains(@class,'text-ellipsis')]")).getText());

        wait.until(driver -> profileButtonsSection().isDisplayed());

        // Step - Change gender
        String changedGender = "Жена";
        driver.findElement(By.id("profileChangeGender")).click();
        driver.findElement(By.id("id_new_gender")).click();
        driver.findElement(By.xpath("//select[@id='id_new_gender']//option[@value='2']")).click();
        driver.findElement(By.xpath("//button[contains(@class,'btn-change-gender')]")).click();
        wait.until(driver -> profileButtonsSection().isDisplayed());
        verifyText("Gender",changedGender,driver.findElement
                (By.xpath("//div[contains(@class,'profile-row')][3]//div[contains(@class,'text-ellipsis')]")).getText());

        wait.until(driver -> profileButtonsSection().isDisplayed());

        // Step - Change birthdate
        driver.findElement(By.id("profileChangeBirthDate")).click();
        Thread.sleep(1000);
        enterBirthDateField("01.01.1990");
//        driver.findElement(By.xpath("//button[@type='submit']")).click();


    }
}
