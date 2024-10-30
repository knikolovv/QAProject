package tests;

import pages.BasePage;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import tests.extension.MyExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@ExtendWith(MyExtension.class)
public class BaseTest {

    public static LocalDateTime date = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    @BeforeAll
    public static void setUp() {
        BasePage.initDriver();
    }

    @AfterAll
    public static void shutdownDriver() {
        BasePage.shutdownDriver();
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
    public static byte[] screenshot() {
        return ((TakesScreenshot) BasePage.driver).getScreenshotAs(OutputType.BYTES);
    }


}
