import baseTest.BaseTest;
import listeners.AllureListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;

@Listeners(AllureListener.class)
public class ITTest2 extends BaseTest {

    private SelectPreconditionPage selectPreconditionPage;
    private SelectRegisteredOwnerPage selectRegisteredOwnerPage;
    private SelectVehiclePage vehiclePage;

    @Test(description = "Negative test with insurance start date in the past", alwaysRun = true)
    public void testWithInsuranceStartDateInPast() {
        selectPreconditionPage = new SelectPreconditionPage();
        selectPreconditionPage.passIncorrectInceptionDateAndVerifyValidation
                ("02032020",
                        "Hups! Dieses Datum liegt in der Vergangenheit. Bitte überprüfe deine Eingabe.");
    }

    @Test(description = "Negative test with insurance start date in the future", alwaysRun = true)
    public void testWithInsuranceStartDateInFuture() {
        selectPreconditionPage = new SelectPreconditionPage();
        selectPreconditionPage.passIncorrectInceptionDateAndVerifyValidation
                ("09032022",
                        "Hups! Dieses Datum liegt zu weit in der Zukunft. Bitte überprüfe deine Eingabe.");
    }

    @Test(description = "Negative test with searching car that not exist", alwaysRun = true)
    public void testWithCarThatNotExist() {
        selectPreconditionPage = new SelectPreconditionPage();
        selectPreconditionPage.selectPreconditions("01062020");
        selectRegisteredOwnerPage = new SelectRegisteredOwnerPage();
        selectRegisteredOwnerPage.chooseSharedNoAndNewCar();
        vehiclePage = new SelectVehiclePage();
        vehiclePage.chooseCarThatNotExist("FSO",
                "Diese Marke ist uns nicht bekannt. Bitte überprüfe deine Eingabe.");
    }

    @Test(description = "Negative test with searching car by HSN/TSN", alwaysRun = true)
    public void testWithCarByIncorrectHsnTsn() {
        selectPreconditionPage = new SelectPreconditionPage();
        selectPreconditionPage.selectPreconditions("01062020");
        selectRegisteredOwnerPage = new SelectRegisteredOwnerPage();
        selectRegisteredOwnerPage.chooseSharedNoAndNewCar();
        vehiclePage = new SelectVehiclePage();
        vehiclePage.chooseCarByHsnTsnThatNotExist("1111", "1111",
                "Wir konnten kein passendes Fahrzeug ermitteln. Bitte gib nur PKW ein - keine Roller," +
                        " Motorräder, LKW, Wohnmobile oder Anhänger.");
    }
}
