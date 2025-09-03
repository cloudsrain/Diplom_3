package client;

import com.google.gson.Gson;
import data.TestData;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserLogin;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Client {

    private static final String USER_CREATION_PATH = "/api/auth/register";
    private static final String DELETE_USER_PATH = "/api/auth/user";
    private static final String USER_LOGIN_PATH = "/api/auth/login";

    private final Gson gson = new Gson();

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user){
        return given()
                .log().all()
                .baseUri(TestData.BASE_URL)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(USER_CREATION_PATH)
                .then()
                .log().all();
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken){
        given()
                .log().all()
                .baseUri(TestData.BASE_URL)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(DELETE_USER_PATH)
                .then()
                .log().all();
    }

    @Step("Логин пользователя")
    public ValidatableResponse loginUser(UserLogin credentials){
        return given()
                .log().all()
                .baseUri(TestData.BASE_URL)
                .header("Content-Type", "application/json")
                .body(credentials)
                .when()
                .post(USER_LOGIN_PATH)
                .then()
                .log().all();
    }

    @Step("Успешное создание пользователя")
    public static String successfulCreation(ValidatableResponse response) {
        response.assertThat().statusCode(200)
                .body("success", equalTo(true));

        return response.extract()
                .path("accessToken")
                .toString()
                .replace("Bearer ", "");
    }



}
