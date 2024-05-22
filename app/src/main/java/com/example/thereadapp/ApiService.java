package com.example.thereadapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiService {
    @GET("api/v2/facts/random?language=en")
    Call<ResponseObject> getRandomFact();
}

