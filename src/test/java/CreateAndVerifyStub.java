import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class CreateAndVerifyStub {
    public void createStub(){
        configureFor("localhost", 8888);
        stubFor(get(urlEqualTo("/api/services")).
                willReturn(aResponse().withStatus(200).withBody("Content goes here")
                .withHeader("Content-Type", "application/json")));
    }

    @Test
    public void verifyStub(){
        createStub();
        RestAssured.baseURI = "http://localhost:8888";
        Response response = RestAssured.given().log().all().when().get("/api/services");
        System.out.println("Status Code: " +response.getStatusCode());
        System.out.println("Response Body: " +response.asString());
    }



}
