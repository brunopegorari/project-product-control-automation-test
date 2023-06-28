package automation.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BaseTest {
    public static WebDriver driver;
    public static final String url_base = "file:///C:.../Project/system_to_be_tested/login.html";
    public static final String driver_path = "src/test/java/automation/resource/chromedriver.exe";

    @BeforeAll
    public static void beforeTest(){
        System.setProperty("webdriver.chrome.driver", driver_path); //Specify Configuration
        //driver = new FirefoxDriver(); //Instantiate Firefox
        driver = new ChromeDriver(); //Instance Chrome Driver
        //driver.manage().window().maximize(); //Maximize the browser
        driver.get(url_base);
    }

    @AfterAll
    public static void teardown(){
        driver.quit();
    }
}
