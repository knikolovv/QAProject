import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Props {

    private static final Properties p = new Properties();

    /*
     * Static block used to load properties from the file 'config.properties'
     */
    static {
        try (FileReader reader = new FileReader("src/test/resources/config.properties")) {
            p.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUrl() {
        return p.getProperty("url");
    }

    public static String getEmail() {
        return p.getProperty("email");
    }

    public static String getPassword() {
        return p.getProperty("password");
    }

    public static String getName() {
        return p.getProperty("name");
    }

    public static String getGender() {
        return p.getProperty("gender");
    }

    public static String getBirthDate() {
        return p.getProperty("birthDate");
    }

    public static String getDiscount() {
        return p.getProperty("discount");
    }
}
