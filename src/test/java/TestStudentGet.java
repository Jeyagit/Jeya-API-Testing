import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestStudentGet {
    Util util = new Util();
    /* wiremock server has to be started in port 8888 before running this test */
    @DataProvider()
    public Object[] testData() throws IOException {
        System.out.println("Inside Data Provider");
        return util.readFromExcelAndReturnDataAsList("C:\\Users\\velmu\\IdeaProjects\\Jeya-API-Testing\\src\\test\\resources\\inputData.xlsx","sheet1");
    }

    @Test(dataProvider = "testData")
    public void verifyMockService(Object[] inputDataMap)  throws IOException, JSONException {
        RestAssured.baseURI = "http://localhost:8888";
        Response response = RestAssured.given()
                .header("studentID",101)
                .when()
                .get("/student");
        System.out.println("Status Code: " +response.getStatusCode());
        System.out.println("Response Body: " +response.asString());
        String expectedResponse = util.readFromFileAndConvertToString("C:\\Users\\velmu\\IdeaProjects\\Jeya-API-Testing\\src\\test\\resources\\output.json");
        //List<Map<String,Object>> inputDataMap = util.readFromExcelAndReturnDataAsList("C:\\Users\\velmu\\IdeaProjects\\Jeya-API-Testing\\src\\test\\resources\\inputData.xlsx","sheet1");
        //System.out.println("inputDataMap" +inputDataMap);
        JSONAssert.assertEquals(expectedResponse,response.asString(), JSONCompareMode.LENIENT);
    }
}
