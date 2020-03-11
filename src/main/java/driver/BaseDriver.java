package driver;

import listeners.HighLighterEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class BaseDriver {

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static void instantiateWebDriver(String driverName) {
        if (driverName.equalsIgnoreCase("chrome")) {
            setWebDriver(new ChromeDriver());
        } else {
            setWebDriver(new FirefoxDriver());
        }
    }

    public static void quitDriver() {
        webDriver.get().quit();
    }

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    private static void setWebDriver(WebDriver driver) {
        EventFiringWebDriver events = new EventFiringWebDriver(driver);
        events.register(new HighLighterEventListener());
        webDriver.set(events);
    }
}
