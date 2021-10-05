package com.acme.dbo.ut.account;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class ClientControllerRetroFitUnitTest implements ClientsEndpoint{
    private ClientService service;
    private RequestSpecification request;
    private Connection connection;

    @BeforeEach
    public void init() throws SQLException {
        connection = DriverManager.getConnection("jdbc:derby://localhost/dbo-db");
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://localhost:8080/dbo/api/")
                .build();
        service = retrofit.create(ClientService.class);
    }

@AfterEach
public void disable() throws SQLException {
        connection.close();
}

    @Test
    public void shouldPost() throws IOException {
        service.createClient(new Client("sobaka@sobaka.ru", "somesalt",
                UUID.randomUUID().toString())).execute();
    }

    @Test
    public void shouldGetAllClients() throws IOException {
        System.out.println(service.getClients().execute().body());
    }
}
