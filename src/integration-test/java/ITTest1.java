import baseTest.BaseTest;
import listeners.AllureListener;
import org.apache.poi.xssf.streaming.SheetDataWriter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utils.TestData;

import java.util.Map;

@Listeners(AllureListener.class)
public class ITTest1 extends BaseTest {

    private SelectPreconditionPage selectPreconditionPage;
    private SelectRegisteredOwnerPage selectRegisteredOwnerPage;
    private SelectVehiclePage vehiclePage;
    private ShowHsnTsnCarPage hsnTsnCarPage;

    @Test(description = "Happy path tests with different car brands and models",
            dataProvider = "data", dataProviderClass = TestData.class, alwaysRun = true)
    public void testWithSearchingDifferentCarsAndModels(Map<Object, Object> testData) {
        selectPreconditionPage = new SelectPreconditionPage();
        selectPreconditionPage.selectPreconditions("01062020");
        selectRegisteredOwnerPage = new SelectRegisteredOwnerPage();
        selectRegisteredOwnerPage.chooseSharedNoAndNewCar();
        vehiclePage = new SelectVehiclePage();
        vehiclePage.chooseCarSpecifications(testData);
        vehiclePage.verifyIfFieldTextIsDisplayedCorrectly("Wann wurdest du geboren?");
    }

    @Test(description = "Positive test with searching car by HSN/TSN", alwaysRun = true)
    public void testWithCarByCorrectHsnTsn() {
        selectPreconditionPage = new SelectPreconditionPage();
        selectPreconditionPage.selectPreconditions("01062020");
        selectRegisteredOwnerPage = new SelectRegisteredOwnerPage();
        selectRegisteredOwnerPage.chooseSharedNoAndNewCar();
        vehiclePage = new SelectVehiclePage();
        vehiclePage.chooseCarByHsnTsn("7118", "AAV");
        hsnTsnCarPage = new ShowHsnTsnCarPage();
        hsnTsnCarPage.verifySearchedCarByHsnTsn
                ("MAZDA MAZDA 3 STH 2.0, Limousine, Benzin, 1999 cc, 110/150 KW/PS");
        hsnTsnCarPage.verifyIfFieldTextIsDisplayedCorrectly("Wann wurdest du geboren?");
    }
}
