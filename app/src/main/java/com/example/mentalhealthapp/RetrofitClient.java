package com.example.mentalhealthapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static Retrofit flaskRetrofit = null;

    public static ApiService getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }

    public static ApiServiceFlask getApiServiceFlask() {
        if (flaskRetrofit == null) {
            flaskRetrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/") // Flask API URL (localhost for emulator)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return flaskRetrofit.create(ApiServiceFlask.class);
    }
}
