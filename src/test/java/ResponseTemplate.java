import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ResponseTemplate {

    @Rule
    public WireMockRule wm = new WireMockRule(new WireMockConfiguration().port(9999)
            .extensions(new ResponseTemplateTransformer(false)));

    public void createStubForRequestHeader(){
        wm.stubFor(get(urlEqualTo("/api/services")).
                willReturn(aResponse().withStatus(200).withBody("Service ID {{request.headers.serviceID}} is here.")
                        .withTransformers("response-template")));
    }

    public void createStubForRequestPath(){
        wm.start();
        wm.stubFor(get(urlEqualTo("/api/services")).
                willReturn(aResponse().withStatus(200).withBody("{{request.path.[0]}}")
                        .withTransformers("response-template")));
    }

    public void createStubForRequestBody(){
        wm.start();
        wm.stubFor(post(urlEqualTo("/api/services")).
                willReturn(aResponse().withStatus(200).withBody("Service name is {{jsonPath request.body '$.serviceName'}}")
                        .withTransformers("response-template")));
    }

    @Test
    public void verifyStubRequestHeader(){
        wm.start();
        createStubForRequestHeader();
        RestAssured.baseURI = "http://localhost:9999";
        Response response = RestAssured.given().log().all().when()
                .header("serviceID","ser101")
                .get("/api/services");
        wm.stop();
        System.out.println("Status Code: " +response.getStatusCode());
        System.out.println("Response Body: " +response.asString());
    }

    @Test
    public void verifyStubRequestPath(){
        wm.start();
        createStubForRequestPath();
        RestAssured.baseURI = "http://localhost:9999";
        Response response = RestAssured.given().log().all().when().get("/api/services");
        wm.stop();
        System.out.println("Status Code: " +response.getStatusCode());
        System.out.println("Response Body: " +response.asString());
    }

    @Test
    public void verifyStubRequestBody(){
        wm.start();
        createStubForRequestBody();
        RestAssured.baseURI = "http://localhost:9999";
        Response response = RestAssured.given().log().all().when()
                .body("{\n" +
                        "\"serviceID\" : \"ser102\",\n" +
                        "\"serviceName\" : \"Apiservice\"\n" +
                        "}")
                .post("/api/services");
        wm.stop();
        System.out.println("Status Code: " +response.getStatusCode());
        System.out.println("Response Body: " +response.asString());
    }
}
