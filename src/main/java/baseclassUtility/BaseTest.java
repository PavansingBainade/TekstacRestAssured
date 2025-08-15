package baseclassUtility;
import java.io.FileNotFoundException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class BaseTest {
	static RequestSpecification reqSpec;
    static ResponseSpecification resSpec;

    public static RequestSpecification setup() throws FileNotFoundException {
        if (reqSpec == null) {

            String baseURI = Configmanager.getProperty("baseURI"); 
            String username = Configmanager.getProperty("username");
            String password = Configmanager.getProperty("password");
            String loginPath = Configmanager.getProperty("login");
            String tokenPath = Configmanager.getProperty("token");
            
            Response loginRes = given()
                    .baseUri(baseURI)
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("username", username)
                    .formParam("password", password)
                    .post(loginPath)
                    .then()
                    .extract().response();

            String authCode = loginRes.jsonPath().getString("auth_code");
            
            Response tokenRes = given()
                    .baseUri(baseURI)
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("auth_code", authCode)
                    .post(tokenPath)
                    .then()
                    .extract().response();

            String accessToken = tokenRes.jsonPath().getString("access_token");

            
            reqSpec = new RequestSpecBuilder()
                    .setBaseUri(baseURI)
                    .setAccept(ContentType.JSON)
                    .setContentType(ContentType.JSON)
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .build();
        }
        return reqSpec;
    }
    public static ResponseSpecification responseSpec() {
        if (resSpec == null) {
            resSpec = new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.JSON)
                    .build();
        }
        return resSpec;
    }	
}
