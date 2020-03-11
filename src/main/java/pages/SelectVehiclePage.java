package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import java.util.Map;

public class SelectVehiclePage extends BasePage {

    @FindBy(how = How.NAME, using = "list")
    private WebElement chooseFromListBtn;

    @FindBy(how = How.NAME, using = "hsnTsn")
    private WebElement hsnTsnButton;

    @FindBy(how = How.NAME, using = "hsn")
    private WebElement hsnButton;

    @FindBy(how = How.NAME, using = "tsn")
    private WebElement tsnButton;

    @FindBy(how = How.NAME, using = "makeFilter")
    private WebElement filterCarBrands;

    @FindBy(how = How.NAME, using = "monthYearFirstRegistered")
    private WebElement monthYearFirstRegisteredInput;

    @FindBy(how = How.CSS, using = "p > span")
    private WebElement carNotExistLabel;

    @FindBy(how = How.CSS, using = "#root > div > div.AlertsView__container--1qVrW > div > div > div > div > span")
    private WebElement toastMessage;

    @Step("Choose car specifications")
    public void chooseCarSpecifications(Map<Object, Object> map) {
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key.equals("registrationDate")) {
                chooseFirstRegistrationDate((String) value);
            } else {
                click(findButtonWithSpecificFontText((String) value));
            }
        }
        submitForm();
    }

    private void chooseFirstRegistrationDate(String monthAndYearDate) {
        clearInputFieldAndFillIn(monthYearFirstRegisteredInput, monthAndYearDate);
    }

    @Step("Choose car using car filter that not exist and verify text")
    public void chooseCarThatNotExist(String make, String expectedTest) {
        sendKeys(filterCarBrands, make);
        verifyTextForCarThatNotExist(expectedTest);
    }

    private void verifyTextForCarThatNotExist(String expectedTest) {
        Assert.assertEquals(getText(carNotExistLabel), expectedTest);
    }

    @Step("Choose car using HSN/TSN")
    public void chooseCarByHsnTsnThatNotExist(String hsn, String tsn, String expectedText) {
        chooseCarByHsnTsn(hsn, tsn);
        Assert.assertEquals(getText(toastMessage), expectedText);
    }

    public void chooseCarByHsnTsn(String hsn, String tsn){
        click(hsnTsnButton);
        sendKeys(hsnButton, hsn);
        sendKeys(tsnButton, tsn);
        submitForm();
    }
}
