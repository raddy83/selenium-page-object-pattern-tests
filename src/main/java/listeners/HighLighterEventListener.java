package listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class HighLighterEventListener extends AbstractWebDriverEventListener {
    private WebElement lastElement;

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        if(lastElement!=null){
            try {
                ((JavascriptExecutor)driver).executeScript(
                        "arguments[0].style.border='none'",lastElement
                );
            } catch (Exception e) {
            }
        }
        lastElement=null;
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        lastElement = element;
        ((JavascriptExecutor)driver).executeScript(
            "arguments[0].style.border='10px dashed green'",element
        );
    }
}
