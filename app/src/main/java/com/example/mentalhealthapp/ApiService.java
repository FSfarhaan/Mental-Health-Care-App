package com.example.mentalhealthapp;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/signup")
    Call<Void> signup(@Body UserModel user);

    @POST("/login")
    Call<Void> login(@Body UserModel user);
}
