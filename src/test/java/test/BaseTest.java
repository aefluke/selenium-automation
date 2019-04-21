package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static final String SELENIUM_HUB_URL = "http://0.0.0.0:4444/wd/hub";
    private static final String FIREFOX = "firefox";
    public WebDriver driver;


    public void setUp(String browser) throws MalformedURLException {

        DesiredCapabilities dc;
        if (browser.equalsIgnoreCase(FIREFOX)) {
            dc = DesiredCapabilities.firefox();
        } else {
            dc = DesiredCapabilities.chrome();
        }

        driver = new RemoteWebDriver(new URL(SELENIUM_HUB_URL), dc);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
