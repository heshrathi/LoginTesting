package com.example.logintesting;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {

    @Headers("Content-type: application/json")
    //@FormUrlEncoded
    @POST("loginRestApi")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
    //Call<LoginResponse> userLogin(@Field("username") String username, @Field("userpass") String userpass);

}
