import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(DataProviderRunner.class)
public class JUnitDataProviderExampleIT {

    @DataProvider
    public static Object[][] sumTestData() {
        return new Object[][]{
                {2, 2, 4},
                {10, 2, 11},
                {1000000, -1000000, 0}
        };
    }

    @Test
    @UseDataProvider("sumTestData")
    public void dataProviderTest(int a, int b, int expectedSum) {

        Assert.assertEquals(expectedSum, a + b);
    }

}