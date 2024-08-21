import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BaseTest {

    public static WebDriver driver;
    public static LocalDateTime date = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    @BeforeAll
    public static void setUp() {
        BasePage.initDriver();

    }

    @AfterAll
    public static void shutdownDriver() throws IOException {
        takeScreenshot();
        BasePage.shutdownDriver();
    }

    public static void takeScreenshot() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDate = date.format(formatter);

        TakesScreenshot sc = (TakesScreenshot) BasePage.driver;
        File src = sc.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("./Screenshots/" + formattedDate + ".png"));
    }


}
