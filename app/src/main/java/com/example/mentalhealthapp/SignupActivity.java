package com.example.mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    TextInputEditText inputName, inputEmail, inputPassword;
    Button submitBtn, switchToLogin;
    TextView debugText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            inputName = findViewById(R.id.inputName);
            inputEmail = findViewById(R.id.inputEmail);
            inputPassword = findViewById(R.id.inputPassword);
            submitBtn = findViewById(R.id.submitBtn);
            switchToLogin = findViewById(R.id.switchToLogin);
            debugText = findViewById(R.id.debugText);

            submitBtn.setOnClickListener(v -> {
                String name = inputName.getText().toString();
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    UserModel user = new UserModel(name, email, password);

                    ApiService apiService = RetrofitClient.getApiService();
                    Call<Void> call = apiService.signup(user);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();
                            }else{
                                // Toast.makeText(SignupActivity.this, "Signup failed", Toast.LENGTH_SHORT).show();

                                try {
                                    // Get the error response body
                                    String errorBody = response.errorBody().string();
                                    Gson gson = new Gson();
//                                    ErrorResponse errorResponse = gson.fromJson(errorBody, ErrorResponse.class);
//                                    String errorMessage = errorResponse.getMsg();
//                                    // Show the error message in a Toast or TextView
//                                    // Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
//                                    debugText.setText(errorMessage);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable throwable) {
                            // Toast.makeText(SignupActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            debugText.setText(throwable.getMessage());
                        }
                    });

                }
            });

            switchToLogin.setOnClickListener(v -> {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            });

            return insets;
        });
    }
}