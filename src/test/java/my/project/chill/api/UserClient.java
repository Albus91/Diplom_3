package my.project.chill.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.given;
import static my.project.chill.constants.URLs.*;

public class UserClient {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public static class UserLogin {
        private String email;
        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserCreate {
        private String email;
        private String password;
        private String name;
        }

    @Step("Создание пользователя")
    public void createUser(UserCreate user) {
        given()
                .baseUri(CONSTRUCTOR_URL)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(REGISTER_API);
    }

    @Step("Логин за пользователя по апи")
    public String loginUser(UserLogin userLogin) {
        Response resp = given()
                .baseUri(CONSTRUCTOR_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userLogin)
                .when()
                .post(LOGIN_API)
                .then()
                .statusCode(200)
                .log().ifValidationFails()
                .extract().response();
        return resp.jsonPath().getString("accessToken");
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        given()
                .baseUri(CONSTRUCTOR_URL)
                .header("Authorization", accessToken)
                .when()
                .delete(USER_API);
    }


}
