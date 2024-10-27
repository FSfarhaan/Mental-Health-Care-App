package com.example.mentalhealthapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetResponseActivity extends AppCompatActivity {
    Button getResponses;
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_response);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            getResponses = findViewById(R.id.getResponses);
            responseText = findViewById(R.id.responseText);

            getResponses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Integer> responseArray = List.of(3, 3, 3, 3, 3,
                            3, 3, 3, 3, 3,
                            3 ,3 ,3 ,3 , 3);

                    Map<String, Object> responses = new HashMap<>();
                    responses.put("responses", responseArray);

                    ApiServiceFlask apiServiceFlask = RetrofitClient.getApiServiceFlask();
                    Call<PredictionResponse> call = apiServiceFlask.getPrediction(responses);

                    call.enqueue(new Callback<PredictionResponse>() {
                        @Override
                        public void onResponse(Call<PredictionResponse> call, Response<PredictionResponse> response) {
                            PredictionResponse predictionResponse = response.body();
                            if(response.isSuccessful()) {
                                String depressionLevel = predictionResponse.getDepressionLevel();
                                String feedback = predictionResponse.getFeedback();

                                responseText.setText("You have " + depressionLevel + " feedback is " + feedback);
                            } else {
                                responseText.setText(predictionResponse.getError());
                            }
                        }

                        @Override
                        public void onFailure(Call<PredictionResponse> call, Throwable throwable) {
                            //Toast.makeText(GetResponseActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            responseText.setText(throwable.getMessage());
                        }
                    });
                }
            });
            return insets;
        });
    }
}