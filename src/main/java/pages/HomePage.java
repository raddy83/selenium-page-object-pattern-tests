package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {

    @FindBy(how = How.ID, using = "uc-btn-accept-banner")
    private WebElement acceptCookieButton;

    @Step("Open home page")
    public HomePage openHomePage() {
        openPage("https://hello.friday.de/");
        return this;
    }

    @Step("Accept cookies on page")
    public void acceptCookiesOnPage() {
        CookiePage cookiePage = new CookiePage();
        cookiePage.isLoaded();
        click(acceptCookieButton);
    }
}
