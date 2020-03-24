package baseTest;

import driver.BaseDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.HomePage;

public class BaseTest {
    public BaseTest() {
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("browser") String value) {
        if (value.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            BaseDriver.instantiateWebDriver(value);
        } else {
            WebDriverManager.firefoxdriver().setup();
            BaseDriver.instantiateWebDriver(value);
        }
    }

    @BeforeMethod(dependsOnMethods = "setUp")
    public void openBrowserAndAcceptCookies() {
        HomePage hp = new HomePage();
        hp.openHomePage();
        hp.acceptCookiesOnPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        BaseDriver.quitDriver();
    }
}
