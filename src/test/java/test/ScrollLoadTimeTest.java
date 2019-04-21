package test;

import domain.LoadTimeStatus;
import net.lightbody.bmp.core.har.HarTimings;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import report.ImageLoadTimeReport;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

public class ScrollLoadTimeTest extends BaseTest {

    private HomePage homePage;

    @BeforeTest
    @Parameters(value = "browser")
    public void setUp(String browser) throws MalformedURLException {
        super.setUp(browser);
        homePage = new HomePage(driver);
    }

    @Test
    public void name() throws IOException {
        homePage.go();
        homePage.checkLogoDisplay();
        homePage.scrollDownToBottom();
        List<String> boutiqueImageSources = homePage.getBoutiqueImageSources();
        List<LoadTimeStatus> loadTimeStatusList = proxy.getHar().getLog().getEntries().stream()
                .filter(harEntry -> boutiqueImageSources.contains(harEntry.getRequest().getUrl()))
                .map(harEntry ->
                        new LoadTimeStatus(
                                harEntry.getResponse().getStatus(),
                                harEntry.getRequest().getUrl(),
                                calculateResponseTime(harEntry.getTimings()))
                ).collect(Collectors.toList());
        new ImageLoadTimeReport().write(loadTimeStatusList);
    }

    private long calculateResponseTime(HarTimings timing) {
        return timing.getSend() +
                timing.getWait() +
                timing.getReceive();
    }


    @AfterTest
    public void tearDown() {
        super.tearDown();
    }
}
