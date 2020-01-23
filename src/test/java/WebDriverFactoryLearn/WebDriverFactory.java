package WebDriverFactoryLearn;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    private static DriverInterface driverTest;

    public static WebDriver create(String browser){

        if(browser.equalsIgnoreCase(String.valueOf(Browsers.CHROME))) {
            driverTest = new ChromeDriverFactory();
        }
        if(browser.equalsIgnoreCase(String.valueOf(Browsers.FIREFOX))) {
            driverTest = new FirefoxDriverFactory();
        }
        return driverTest.setupDriver();

    }
}
