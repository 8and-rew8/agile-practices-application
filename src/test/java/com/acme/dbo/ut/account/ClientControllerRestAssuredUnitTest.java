package com.acme.dbo.ut.account;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class ClientControllerRestAssuredUnitTest implements ClientsEndpoint{
    private RequestSpecification request;
    private Connection connection;

    @BeforeEach
    public void init() throws SQLException {
        connection = DriverManager.getConnection("jdbc:derby://localhost/dbo-db");
        request = given()
                .baseUri(BASE_URI)
                .port(PORT)
                .basePath(DBO_API)
                .header("X-API-VERSION", 1);
    }

    @AfterEach
    public void disable() throws SQLException {
        connection.close();
    }

    @Test
    public void shouldGetClientById() throws SQLException {
        int clientId;
        try (final PreparedStatement insertClient = connection.prepareStatement(
                "INSERT INTO CLIENT (LOGIN, SECRET, SALT) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            insertClient.setString(1, "sinok@kolobok.ru");
            insertClient.setString(2, UUID.randomUUID().toString());
            insertClient.setString(3, "somesalt");

            assumeTrue(insertClient.executeUpdate() == 1);

            try (final ResultSet resultSet = insertClient.getGeneratedKeys()) {
                assumeTrue(resultSet.next());
                clientId = resultSet.getInt(1);
            }

            request
                    .when()
                    .get(CLIENT_ID, clientId)
                    .then()
                    .statusCode(SC_OK)
                    .body("login", equalTo("sinok@kolobok.ru"));
        } finally {
            try (final PreparedStatement deleteClient = connection.prepareStatement(
                    "DELETE FROM Client WHERE LOGIN=?")) {
                deleteClient.setString(1, "sinok@kolobok.ru");
                assumeTrue(deleteClient.executeUpdate() == 1);
            }
        }
    }

    @Test
    public void shouldDeleteClientById() throws SQLException {
        int clientId;
        try (final PreparedStatement insertClient = connection.prepareStatement(
                "INSERT INTO CLIENT (LOGIN, SECRET, SALT) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            insertClient.setString(1, "sinok@kolobok.ru");
            insertClient.setString(2, UUID.randomUUID().toString());
            insertClient.setString(3, "somesalt");

            assumeTrue(insertClient.executeUpdate() == 1);

            try (final ResultSet resultSet = insertClient.getGeneratedKeys()) {
                assumeTrue(resultSet.next());
                clientId = resultSet.getInt(1);
            }

            request.when().delete(CLIENT_ID, clientId).then().statusCode(SC_OK);
        } finally {
            try (final PreparedStatement deleteClient = connection.prepareStatement(
                    "DELETE FROM Client WHERE LOGIN=?")) {
                deleteClient.setString(1, "sinok@kolobok.ru");
                assumeTrue(deleteClient.executeUpdate() == 0);
            }
        }

    }
}
