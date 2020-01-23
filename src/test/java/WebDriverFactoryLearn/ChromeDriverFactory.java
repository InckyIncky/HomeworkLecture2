package WebDriverFactoryLearn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverFactory implements DriverInterface{

    @Override
    public WebDriver setupDriver() {
        return new ChromeDriver();
    }
}
