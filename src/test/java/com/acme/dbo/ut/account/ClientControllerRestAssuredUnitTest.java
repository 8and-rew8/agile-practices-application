package com.acme.dbo.ut.account;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class ClientControllerRestAssuredUnitTest implements ClientsEndpoint{
    private RequestSpecification request;

    @BeforeEach
    public void init() {
        request = given()
                .baseUri(BASE_URI)
                .port(PORT)
                .basePath(DBO_API)
                .header("X-API-VERSION", 1);
    }

    @Test
    public void shouldGetClientById() {
        request
                .when()
                .get(CLIENT_ID, 1)
                .then()
                .statusCode(SC_OK)
                .body("id", equalTo(1)
                        , "login", equalTo("admin@acme.com"));
    }

    @Test
    public void shouldDeleteClientById() {
        request.when().delete(CLIENT_ID, 4).then().statusCode(SC_OK);
    }
}
