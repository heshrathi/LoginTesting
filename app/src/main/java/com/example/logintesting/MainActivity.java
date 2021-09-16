package com.example.logintesting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    EditText username, password;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.usernameID);
        password = findViewById(R.id.passwordID);
        btnLogin = findViewById(R.id.loginID);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Username / Password Required", Toast.LENGTH_LONG).show();
                } else {
                    //proceed to login
                    login();
                }

            }
        });
    }


    public void login() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username.getText().toString());
        loginRequest.setPassword(password.getText().toString());

        Call<LoginResponse> loginResponseCall = APIClient.getUserService().userLogin(loginRequest);
        //Call<LoginResponse> loginResponseCall = APIClient.getUserService().userLogin(username.getText().toString(), password.getText().toString());
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.i("response", response.raw().toString());

                if (response.isSuccessful()) {

                    Log.i("success", "logined");
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();

                    if(LoginResponse.getMessage().equals("logined successfully")){
                        //login start main activity
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        //intent.putExtra("username", username);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }

//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            startActivity(new Intent(MainActivity.this, HomeActivity.class).putExtra("data", loginResponse.getMessage()));
//                        }
//                    }, 700);

                } else {
                    Log.i("failed", "problem");
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i("error", "cache");
                Log.i("response", call.toString());
                Toast.makeText(MainActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}