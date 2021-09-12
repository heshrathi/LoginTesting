package com.example.logintesting;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIClient {

    @FormUrlEncoded
    @POST("loginRestApi")
    Call<ResponseBody> loginRestApi(
            @Field("username") String username,
            @Field("userpass") String password
    );
}
