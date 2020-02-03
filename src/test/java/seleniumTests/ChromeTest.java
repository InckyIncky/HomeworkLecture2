package seleniumTests;

import WebDriverFactoryLearn.WebDriverFactory;
import java.util.*;

import junitListener.MyTestRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;

//@RunWith(MyTestRunner.class)
public class ChromeTest {

    private static WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger("ChromeTestLog");

    @BeforeClass
    public static void setUp() {
        driver = WebDriverFactory.create("ChrOMe");
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        LOGGER.info("webdriver configured");
    }

    @Test
    public void SeleniumOpenOtusPageAssertTitle() {
        LOGGER.info("Starting test 1");
        driver.get("http://otus.ru");
        LOGGER.info("Page otus.ru opened");
        String expectedPageTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        Assert.assertTrue("Test Failed", driver.getTitle().contains(expectedPageTitle));
        if(driver.getTitle().contains(expectedPageTitle)){
            LOGGER.info("Actual text of the title matches the expected text");
        }
        LOGGER.info("Exit test 1");
    }

    @Test
    public void SeleniumOpenOtusPageOpenMyCourses() {
        LOGGER.info("Starting test 2");
        driver.get("http://otus.ru");
        LOGGER.info("Page otus.ru opened again");
        driver.findElement(By.xpath("/html/body/div[1]/div/header[1]/div/div[2]/div[1]/a[1]")).click();
        String expectedMyCoursesPageTitle = "OTUS - Онлайн-образование";
        LOGGER.info("Мои курсы opened");
        Assert.assertTrue("My Courses test failed", driver.getTitle().contains(expectedMyCoursesPageTitle));
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
