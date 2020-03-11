package seleniumTests;

import WebDriverFactoryLearn.WebDriverFactory;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Properties;

//@RunWith(MyTestRunner.class)
public class ChromeTest {


    private static WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger("ChromeTestLog");

    @BeforeClass
    public static void setUp() {

        Properties props = new Properties();


        try(FileInputStream is = new FileInputStream
                ("src/test/resources/config.properties");) {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browser = props.getProperty("browser");

        driver = WebDriverFactory.create(browser);
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
