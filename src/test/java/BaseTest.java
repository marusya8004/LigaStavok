import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static initialDriver.MultiToneChrome.getInstance;

public class BaseTest {

    public WebDriver driver;

    @BeforeTest
    public WebDriver setUp() {
        driver = getInstance().getDriver();
        return driver;
    }

    @AfterTest
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}