import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

//TODO ASK Ne znam kakvo trqbva da razmenq v BaseTest i BasePage
public class BaseTest  {
    public static Properties p = new Properties();
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static LocalDateTime date = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    @BeforeAll
    public static void setUp() {
            try {
                p.load(new FileReader("src/test/resources/config.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            driver.manage().window().setPosition(new Point(-1500,0));
//            driver.manage().window().maximize();
            driver.get(p.getProperty("url"));

    }

    @AfterAll
    public static void shutdownDriver() throws IOException {
            takeScreenshot();
            driver.quit();
    }

    public static void takeScreenshot() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDate = date.format(formatter);

        TakesScreenshot sc = (TakesScreenshot) driver;
        File src = sc.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("./Screenshots/" + formattedDate + ".png"));
    }


}
