package br.com.portfolio.clients;

import br.com.portfolio.helpers.HelperFixture;
import br.com.portfolio.specs.ApiSpecs;
import io.restassured.response.Response;

import java.util.Map;

import static br.com.portfolio.enums.Endpoints.ENDPOINT_LOGIN;
import static br.com.portfolio.enums.Endpoints.ENDPOINT_USERS;
import static io.restassured.RestAssured.given;

public class ApiClient {

    public Response login(String fixtureName) {
        String credentialsJson = HelperFixture.loadFixture(fixtureName);
        Response response = given()
                .spec(ApiSpecs.getRequestBuilderSpec())
                .body(credentialsJson)
                .when()
                .post(ENDPOINT_LOGIN.getEndpoints())
                .then()
                .extract()
                .response();
        return response;
    }

    public Response getUsers(String token) {
        return given()
                .spec(ApiSpecs.getRequestBuilderAuthenticationSpec(token))
                .when()
                .get(ENDPOINT_USERS.getEndpoints());
    }

    public Response getUsers(String token, Map<String, Object> queryParams) {
        return given()
                .spec(ApiSpecs.getRequestBuilderAuthenticationSpec(token))
                .queryParams(queryParams)
                .when()
                .get(ENDPOINT_USERS.getEndpoints());
    }

    public Response getUserById(String token, String userId) {
        return given()
                .spec(ApiSpecs.getRequestBuilderAuthenticationSpec(token))
                .when()
                .get(ENDPOINT_USERS.getEndpoints() + "/" + userId);
    }

    public Response postUser(String token, String body) {
        return given()
                .spec(ApiSpecs.getRequestBuilderAuthenticationSpec(token))
                .body(body)
                .post(ENDPOINT_USERS.getEndpoints());
    }

    public Response putUser(String token, String userId, String body) {
        return given()
                .spec(ApiSpecs.getRequestBuilderAuthenticationSpec(token))
                .body(body)
                .put(ENDPOINT_USERS.getEndpoints() + "/" + userId);
    }

    public Response deleteUser(String token, String userId) {
        return given()
                .spec(ApiSpecs.getRequestBuilderAuthenticationSpec(token))
                .delete(ENDPOINT_USERS.getEndpoints() + "/" + userId);
    }
}

