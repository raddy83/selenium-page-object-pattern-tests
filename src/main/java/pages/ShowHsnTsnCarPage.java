package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class ShowHsnTsnCarPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//div[@data-test-id='carLabel']")
    private WebElement carLabel;

    @Step("Verify searched car by hsn/tsn")
    public void verifySearchedCarByHsnTsn(String expectedText) {
        Assert.assertEquals(getText(carLabel), expectedText);
        submitForm();
    }
}
