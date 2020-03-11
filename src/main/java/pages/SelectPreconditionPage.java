package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class SelectPreconditionPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[@data-test-id=" +
            "\"quoting.selectPrecondition.keepingCarquoting.selectPrecondition.keepingCar.subLine\"]")
    private WebElement keepingCarButton;

    @FindBy(how = How.XPATH, using = "//button[@data-test-id=" +
            "\"quoting.selectPrecondition.buyingCarquoting.selectPrecondition.buyingCar.subLine\"]")
    private WebElement buyingCarButton;

    @FindBy(how = How.NAME, using = "inceptionDate")
    private WebElement inceptionDateInput;

    @FindBy(how = How.CSS, using = "div.ValidationLabel__validationLabel--2km9U > span")
    private WebElement validationLabel;

    @Step("Select preconditions")
    public void selectPreconditions(String inceptionDate){
        chooseBuyingCarButton();
        setInceptionDateInput(inceptionDate);
        submitForm();
    }

    @Step("Pass incorrect inception date and verify validation text")
    public void passIncorrectInceptionDateAndVerifyValidation(String inceptionDate, String expectedText){
        setInceptionDateInput(inceptionDate);
        Assert.assertEquals(getText(validationLabel), expectedText);
    }

    private void chooseBuyingCarButton() {
        click(buyingCarButton);
    }

    private void setInceptionDateInput(String inceptionDate) {
        clearInputFieldAndFillIn(inceptionDateInput, inceptionDate);
    }

}
