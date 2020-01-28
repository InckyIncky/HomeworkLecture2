package WebDriverFactoryLearn;

import org.openqa.selenium.WebDriver;

import static WebDriverFactoryLearn.Browsers.CHROME;
import static WebDriverFactoryLearn.Browsers.FIREFOX;

public class WebDriverFactory {

    private static Browsers defineBrowser(String browser) {
        if (browser.equalsIgnoreCase(String.valueOf(CHROME))) {
            return CHROME;
        }
        if (browser.equalsIgnoreCase(String.valueOf(FIREFOX))) {
            return FIREFOX;

        } else return CHROME;

    }

    public static WebDriver create(String browser) {

        DriverInterface driverTest;
        switch(defineBrowser(browser)) {
            case CHROME:
                driverTest = new ChromeDriverFactory();
                break;
            case FIREFOX:
                driverTest = new FirefoxDriverFactory();
                break;
            default:
                driverTest = new ChromeDriverFactory();
        }

        return driverTest.setupDriver();

    }
}
