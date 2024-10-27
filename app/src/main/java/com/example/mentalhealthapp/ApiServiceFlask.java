package com.example.mentalhealthapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import java.util.Map;

public interface ApiServiceFlask {
    @POST("/predict")
    Call<PredictionResponse> getPrediction(@Body Map<String, Object> responses);
}
