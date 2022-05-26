import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelAsDataProvider extends Util{
    @DataProvider()
    public Object[] sumTestData() throws IOException {
        System.out.println("Inside Data Provider");
        return readFromExcelAndReturnDataAsList("C:\\Users\\velmu\\IdeaProjects\\Jeya-API-Testing\\src\\test\\resources\\inputData.xlsx","sheet1");
    }

    @Test(dataProvider = "sumTestData")
    public void dataProviderTest(Object[] inputDataMap) {
        //Assert.assertEquals(expectedSum, a + b);
        System.out.println("Test Called");
    }

}