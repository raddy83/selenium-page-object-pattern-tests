package pages;

import driver.BaseDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Clock;
import java.time.Duration;

public class CookiePage extends SlowLoadableComponent {

    private WebDriver driver;

    @FindBy(how = How.ID, using = "uc-btn-accept-banner")
    private WebElement cookieButton;

    public CookiePage() {
        super(Clock.systemDefaultZone(), 60);
        driver = BaseDriver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        new WebDriverWait(driver, 60)
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions
                        .elementToBeClickable(cookieButton));
    }
}
