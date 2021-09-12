package com.example.logintesting;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String url = "http://15.207.242.212/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null)
            mInstance = new RetrofitClient();
        return mInstance;
    }

    public APIClient getApi() {
        return retrofit.create(APIClient.class);
    }

}
