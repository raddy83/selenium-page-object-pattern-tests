package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestData {

    @DataProvider(name = "data")
    public static Object[][] dataSupplier() throws IOException {

        File file = new File("src/integration-test/resources/CarData.xlsx");
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        wb.close();
        int lastRowNum = sheet.getLastRowNum();
        int lastCellNum = sheet.getRow(0).getLastCellNum();
        Object[][] obj = new Object[lastRowNum][1];

        for (int i = 0; i < lastRowNum; i++) {
            Map<Object, Object> datamap = new LinkedHashMap<>();
            for (int j = 0; j < lastCellNum; j++) {
                datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
            }
            obj[i][0] = datamap;

        }
        return obj;
    }

}
