import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import javax.swing.text.Utilities;
import java.io.IOException;

public class TestStudentGet {
    Util util = new Util();
    /* wiremock server has to be started in port 888 before running this test */
    @Test
    public void verifyMockService() throws IOException, JSONException {
        RestAssured.baseURI = "http://localhost:8888";
        Response response = RestAssured.given()
                .header("studentID",101)
                .when()
                .get("/student");
        System.out.println("Status Code: " +response.getStatusCode());
        System.out.println("Response Body: " +response.asString());
        String expectedResponse = util.readFromFileAndConvertToString("C:\\Users\\velmu\\IdeaProjects\\Jeya-API-Testing\\src\\test\\resources\\output.json");
        JSONAssert.assertEquals(expectedResponse,response.asString(), JSONCompareMode.LENIENT);
    }
}
