import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginUITest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        //WebDriverManager.chromedriver().browserVersion("125.0").setup();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\cytry\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testSuccessfulLogin() {
            driver.get("http://localhost:8090/login.html");
            WebElement username = driver.findElement(By.id("username"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
            username.sendKeys("testuser");
            password.sendKeys("testpass");
            loginButton.click();
            assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }
    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
