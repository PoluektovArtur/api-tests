import RequestsBodies.AuthRequest;
import ResponseBodies.AuthResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Tag("API")
public class AuthTests {

    @Test
    @DisplayName("Успешная авторизация")
    public void successAuth() {
        Specifications.InstallSpecification(Specifications.requestSpec(Constants.BASE_URL),Specifications.responseSpec200());
        AuthRequest request = new AuthRequest().setEmail("eve.holt@reqres.in").setPassword("cityslicka");
        AuthResponse response = given()
                .body(request)
                .when()
                .post("/api/login")
                .then().log().all()
                .extract().as(AuthResponse.class);
        Assertions.assertFalse(response.getToken().equals(2));
    }
}
