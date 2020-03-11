package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SelectRegisteredOwnerPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[@data-test-id='shared.yes']")
    private WebElement commercialSharedYesBtn;

    @FindBy(how = How.XPATH, using = "//button[@data-test-id='shared.no']//child::img")
    private WebElement commercialSharedNoBtn;

    @FindBy(how = How.XPATH, using = "//button[@data-test-id=" +
            "\"quoting.selectRegisteredOwner.brandNew\"]//child::img")
    private WebElement brandNewCarBtn;

    @FindBy(how = How.XPATH, using = "//button[@data-test-id='quoting.selectRegisteredOwner.used']")
    private WebElement usedCarBtn;

    @Step("Select shared no and new car options")
    public void chooseSharedNoAndNewCar() {
        click(commercialSharedNoBtn);
        click(brandNewCarBtn);
        submitForm();
    }
}
