package test;

import domain.ResponseStatus;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import report.BoutiqueStatusReport;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class BoutiqueResponseStatusTest extends BaseTest {

    private HomePage homePage;

    @BeforeTest
    @Parameters(value = "browser")
    public void setUp(String browser) throws MalformedURLException {
        super.setUp(browser);
        homePage = new HomePage(driver);
    }

    @Test
    public void checkAllBoutiqueLinksAndReportResponseCodes() throws Exception {
        homePage.go();
        homePage.checkLogoDisplay();

        List<ResponseStatus> responseStatusList = homePage.getBoutiqueLinks()
                .stream()
                .map(this::getStatus)
                .collect(Collectors.toList());

        new BoutiqueStatusReport().write(responseStatusList);
    }

    private ResponseStatus getStatus(String s) {
        try {
            return new ResponseStatus(getResponseCode(s), s);
        } catch (IOException e) {
            return new ResponseStatus(408, s);
        }
    }

    private Integer getResponseCode(String link) throws IOException {
        return ((HttpURLConnection) new URL(link).openConnection()).getResponseCode();
    }

    @AfterTest
    public void tearDown() {
        super.tearDown();
    }

}
