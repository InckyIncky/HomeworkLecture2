package seleniumTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxTest {
    private static WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger("FirefoxTestLog");


    @BeforeClass
    public static void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        LOGGER.info("webdriver configured");
    }

    @Test
    public void SeleniumOpenOtusPage() {
        LOGGER.info("Starting test 1");
        driver.get("http://otus.ru");
        LOGGER.info("Page otus.ru opened");
        String expectedPageTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        Assert.assertTrue("Test failed", driver.getTitle().contains(expectedPageTitle));
        if (driver.getTitle().contains(expectedPageTitle)) {
            LOGGER.info("Actual text of the title matches the expected text");
        }
        LOGGER.info("Exit test 1");
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
