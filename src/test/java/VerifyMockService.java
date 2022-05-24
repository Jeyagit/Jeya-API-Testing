import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class VerifyMockService {
    /* wiremock server has to be started in port 888 before running this test */
    @Test
    public void verifyMockService(){
        RestAssured.baseURI = "http://localhost:8888";
        Response response = RestAssured.given()
                .header("studentID",101)
                .when()
                .get("/student");
        System.out.println("Status Code: " +response.getStatusCode());
        System.out.println("Response Body: " +response.asString());
    }
}
