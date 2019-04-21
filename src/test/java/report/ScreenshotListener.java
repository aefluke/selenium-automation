package report;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import test.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotListener extends TestListenerAdapter {

    private static final String FORMAT = ".png";
    private static final String FAILURE_SCREENSHOTS = "failure_screenshots";
    private static final String TARGET_SUREFIRE_REPORTS = "/target/surefire-reports";

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        String methodName = iTestResult.getName();
        WebDriver driver = ((BaseTest) iTestResult.getInstance()).driver;
        if (!iTestResult.isSuccess()) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                File destFile = new File(createScreenshotFilePath(methodName));
                FileUtils.copyFile(scrFile, destFile);
                addScreenshotToReport(destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void addScreenshotToReport(File destFile) {
        Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
    }

    private String createScreenshotFilePath(String methodName) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_hh_mm_ss");
        String currentDateTime = dateTimeFormatter.format(LocalDateTime.now());

        return getReportDirectory() + "/" + FAILURE_SCREENSHOTS + "/" + methodName + "_" + currentDateTime + FORMAT;
    }

    private String getReportDirectory() {
        return new File(System.getProperty("user.dir")).getAbsolutePath() + TARGET_SUREFIRE_REPORTS;
    }

}
