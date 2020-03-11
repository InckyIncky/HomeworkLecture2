package WebDriverFactoryLearn;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import static WebDriverFactoryLearn.Browsers.CHROME;
import static WebDriverFactoryLearn.Browsers.FIREFOX;

public class WebDriverFactory {

    private static Browsers defineBrowser(String browser) {

        String val = browser.toUpperCase();
        String valChrome = CHROME.toString().toUpperCase();
        String valFirefox = FIREFOX.toString().toUpperCase();
        if (val.equalsIgnoreCase(valChrome)) {
            return CHROME;
        }
        if (val.equals(valFirefox)) {
            return FIREFOX;

        } else return CHROME;

    }

    public static WebDriver create(String browser) {

        DriverInterface driverTest;
        switch(defineBrowser(browser)) {
            case CHROME:
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                driverTest = new ChromeDriverFactory();
                break;
            case FIREFOX:
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                driverTest = new FirefoxDriverFactory();
                break;
            default:

                driverTest = new ChromeDriverFactory();
        }

        return driverTest.setupDriver();

    }

}
