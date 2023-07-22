import io.github.bonigarcia.wdm.WebDriverManager; // import WebDriver Manager
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class seleniumGrid {
    WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        switch (browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.setHeadless(true); // set headless mode to true
                WebDriverManager.chromedriver().setup(); // use WebDriver Manager to set up Chrome driver
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                driver.manage().deleteAllCookies();

                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
            case "firefox":
                FirefoxOptions options2 = new FirefoxOptions();
                options2.setHeadless(true); // set headless mode to true
                WebDriverManager.firefoxdriver().setup(); // use WebDriver Manager to set up Firefox driver
                driver = new FirefoxDriver(options2);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
        }
    }


    @Test
    public void testLinks() throws InterruptedException {
        driver.get("https://www.bbc.co.uk");
        System.out.println("bor");
        Thread.sleep(5000);
        WebElement e = driver.findElement(By.xpath("//header/nav[1]/div[1]/div[1]/div[2]/ul[2]/li[3]/a[1]/span[1]"));
        System.out.println(e.getText());
        assertTrue(driver.getCurrentUrl().contains("bbc"));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
