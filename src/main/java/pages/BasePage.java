package pages;

import driver.BaseDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.openqa.selenium.support.PageFactory.initElements;

public class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//div[@data-test-id='wizardTitle']")
    private WebElement fieldTitle;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private WebElement submitButton;

    public BasePage() {
        this.driver = BaseDriver.getDriver();
        wait = new WebDriverWait(driver, 30);
        initElements(driver, this);
    }

    public void click(WebElement element) {
        waitUntilVisible(element).click();
    }

    public void sendKeys(WebElement element, String data) {
        waitUntilVisible(element).sendKeys(data);
    }

    public void clearInputFieldAndFillIn(WebElement element, String data) {
        waitUntilVisible(element).clear();
        sendKeys(element, data);
    }

    public void openPage(String url) {
        driver.get(url);
    }

    WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public String getText(WebElement element) {
        return waitUntilVisible(element).getText();
    }

    public WebElement findButtonWithSpecificFontText(String text) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*[contains(text(), '" + text + "'" + ")]//ancestor::button")));
    }

    @Step("Verify whether field text title is correct")
    public void verifyIfFieldTextIsDisplayedCorrectly(String fieldTitleText) {
        Assert.assertEquals(getText(fieldTitle), fieldTitleText);
    }

    public void submitForm() {
        click(submitButton);
    }

    public void clickElementThatMightBeStale(WebElement element) {
        try {
            click(element);
        } catch (Exception e) {

        } finally {
            driver.navigate().refresh();
            click(element);
        }
    }
}
