package com.acme.dbo.ut.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.UUID;

public class ClientControllerRetroFitUnitTest implements ClientsEndpoint{
    private ClientService service;

    @BeforeEach
    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://localhost:8080/dbo/api/")
                .build();
        service = retrofit.create(ClientService.class);
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
