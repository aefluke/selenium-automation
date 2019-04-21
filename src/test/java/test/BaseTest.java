package test;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static final String FIREFOX = "firefox";
    public WebDriver driver;
    public BrowserMobProxy proxy;

    public void setUp(String browser) throws MalformedURLException {

        proxy = new BrowserMobProxyServer();
        proxy.start();
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.PROXY, seleniumProxy);

        if (browser.equals(FIREFOX)) {
            System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.merge(dc);
            driver = new FirefoxDriver(firefoxOptions);

        } else {
            System.setProperty("webdriver.chrome.helper", "chromedriver.exe");
            driver = new ChromeDriver(dc);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        proxy.newHar("test-traffic");
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
            proxy.stop();
        }
    }
}
