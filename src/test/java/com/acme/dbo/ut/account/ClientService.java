package com.acme.dbo.ut.account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import java.util.List;

public interface ClientService {

    @GET("client")
    @Headers("X-API-VERSION:1")
    Call<List<Client>> getClients();

    @POST("client")
    @Headers("X-API-VERSION:1")
    Call<Client> createClient(@Body Client client);
}
