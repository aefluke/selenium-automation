package test;

import data.CsvReader;
import domain.LoginScenario;
import org.testng.annotations.*;
import pages.HomePage;
import report.ScreenshotListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

@Listeners(ScreenshotListener.class)
public class LoginTest extends BaseTest {

    private HomePage homepage;

    @BeforeTest
    @Parameters(value = "browser")
    public void setUp(String browser) throws MalformedURLException {
        super.setUp(browser);
        homepage = new HomePage(driver);
    }

    @Test
    public void runDataDrivenLoginScenarios() throws IOException {
        homepage.go();
        homepage.checkLogoDisplay();
        homepage.openLoginModal();

        getLoginScenarios().forEach(loginScenario -> {

                    homepage.clearInputs();
                    homepage.login(loginScenario.getEmail(), loginScenario.getPassword());

                    assertTrue(
                            loginScenario.getScenarioName(),
                            homepage.checkErrorMessageVisible(loginScenario.getFieldValidationErrorMessage())
                    );
                }
        );
    }

    private List<LoginScenario> getLoginScenarios() throws IOException {
        List<LoginScenario> loginScenarios = new CsvReader().readLoginScenarios();
        assertTrue(!loginScenarios.isEmpty());
        return loginScenarios;
    }

    @AfterTest
    public void tearDown() {
        super.tearDown();
    }
}
