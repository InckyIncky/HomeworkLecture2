package WebDriverFactoryLearn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverFactory implements DriverInterface{

    @Override
    public WebDriver setupDriver() {
        return new FirefoxDriver();
    }
}
