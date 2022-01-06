package com.example.searchncovi.DeclareController;

import com.example.searchncovi.Model.Account;
import com.example.searchncovi.Model.Declarations;
import com.example.searchncovi.Model.RequestBodyDeclare;
import com.example.searchncovi.Model.ResponseEntityAccount;
import com.example.searchncovi.Model.ResponseEntityDeclare;
import com.example.searchncovi.Model.ResponseLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiController {
    String DOMAIN = "https://covid-declare.herokuapp.com/api/";
    Gson gson =new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();

    ApiController apiService = new Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiController.class);

    @GET("accounts")
    Call<ResponseEntityAccount> getAllAccount();

    @GET("declarations/{id}")
    Call<ResponseEntityDeclare> getDeclarationsOfAcc(@Path("id") String id);

    @POST("declarations")
    Call<RequestBodyDeclare> declare(@Body RequestBodyDeclare requestBodyDeclare);

    @POST("accounts/login")
    Call<ResponseLogin> login(@Body Account account);

    @POST("accounts")
    Call<ResponseLogin> register(@Body Account account);

}
