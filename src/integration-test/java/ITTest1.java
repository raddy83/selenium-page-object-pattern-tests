import baseTest.BaseTest;
import listeners.AllureListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utils.TestData;

import java.util.Map;

@Listeners(AllureListener.class)
public class ITTest1 extends BaseTest {

    @Test(description = "Happy path tests with different car brands and models",
            dataProvider = "data", dataProviderClass = TestData.class, alwaysRun = true)
    public void testWithSearchingDifferentCarsAndModels(Map<Object, Object> testData) {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.acceptCookiesOnPage();
        SelectPreconditionPage selectPreconditionPage = new SelectPreconditionPage();
        selectPreconditionPage.selectPreconditions("01062020");
        SelectRegisteredOwnerPage selectRegisteredOwnerPage = new SelectRegisteredOwnerPage();
        selectRegisteredOwnerPage.chooseSharedNoAndNewCar();
        SelectVehiclePage vehiclePage = new SelectVehiclePage();
        vehiclePage.chooseCarSpecifications(testData);
        homePage.verifyIfFieldTextIsDisplayedCorrectly("Wann wurdest du geboren?");
    }

    @Test(description = "Positive test with searching car by HSN/TSN", alwaysRun = true)
    public void testWithCarByCorrectHsnTsn() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.acceptCookiesOnPage();
        SelectPreconditionPage selectPreconditionPage = new SelectPreconditionPage();
        selectPreconditionPage.selectPreconditions("01062020");
        SelectRegisteredOwnerPage selectRegisteredOwnerPage = new SelectRegisteredOwnerPage();
        selectRegisteredOwnerPage.chooseSharedNoAndNewCar();
        SelectVehiclePage vehiclePage = new SelectVehiclePage();
        vehiclePage.chooseCarByHsnTsn("7118", "AAV");
        ShowHsnTsnCarPage hsnTsnCarPage = new ShowHsnTsnCarPage();
        hsnTsnCarPage.verifySearchedCarByHsnTsn
                ("MAZDA MAZDA 3 STH 2.0, Limousine, Benzin, 1999 cc, 110/150 KW/PS");
        homePage.verifyIfFieldTextIsDisplayedCorrectly("Wann wurdest du geboren?");
    }
}
