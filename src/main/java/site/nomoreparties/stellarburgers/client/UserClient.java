package site.nomoreparties.stellarburgers.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import site.nomoreparties.stellarburgers.model.User;

import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String URI = "https://stellarburgers.nomoreparties.site/api/auth/";

    public void create(User user) {
         given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(URI + "register/")
                .then();
    }

    public void delete(String accessToken) {
         given()
                .spec(getBaseSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(URI + "user/")
                .then();
    }

    public ValidatableResponse login(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(URI + "login/")
                .then();
    }

    protected RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(URI)
                .build();
    }
}
