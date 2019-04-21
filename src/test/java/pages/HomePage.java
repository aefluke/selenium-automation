package pages;

import helper.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HomePage extends Page {

    private static final String HOME_PAGE_URL = ROOT_URL + "Butik/Liste/Erkek";

    private final WebDriver driver;
    private final Wait wait;

    @FindBy(id = "logo-icon")
    private WebElement logo;

    @FindBy(className = "butik")
    private List<WebElement> boutiques;

    @FindBy(className = "navigation-icon-user")
    private WebElement loginIcon;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "loginSubmit")
    private WebElement loginSubmitButton;

    @FindBy(id = "errorBox")
    private WebElement errorBox;

    public HomePage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Wait(driver);
    }

    public void go() {
        driver.get(HOME_PAGE_URL);
    }

    public void checkLogoDisplay() {
        wait.forElementToBeDisplayed(5, logo, "Logo");
    }

    public List<String> getBoutiqueLinks() {
        return boutiques.stream()
                .map(webElement -> webElement
                        .findElement(By.tagName("a"))
                        .getAttribute("href")
                ).collect(Collectors.toList());
    }

    public List<String> getBoutiqueImageSources() {
        return boutiques.stream()
                .map(webElement -> webElement
                        .findElement(By.tagName("img"))
                        .getAttribute("src")
                ).collect(Collectors.toList());
    }

    public void openLoginModal() {
        loginIcon.click();
        wait.forElementToBeDisplayed(5, emailInput, "Email");
        wait.forElementToBeDisplayed(5, passwordInput, "Password");
        wait.forElementToBeDisplayed(5, loginSubmitButton, "Login button");
    }

    public void login(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginSubmitButton.click();
    }

    public void clearInputs() {
        emailInput.clear();
        passwordInput.clear();
    }

    public Boolean checkErrorMessageVisible(String errorMessage) {
        wait.forElementToBeDisplayed(5, errorBox, "Error message");
        return errorBox.getText().contains(errorMessage);
    }

    public void scrollDownToBottom() {
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        IntStream.range(0, 10).forEach(o -> {
            jsx.executeScript("window.scrollBy(0,550)", "");
            wait.explicitlyFor(2);
        });
    }


}
