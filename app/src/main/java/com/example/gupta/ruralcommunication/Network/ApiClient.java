package com.example.gupta.ruralcommunication.Network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vasudev on 3/9/2018.
 */

public class ApiClient {
    private static Retrofit retrofit;
    private static ApiService apiInterface;
    public static ApiService getApiInterface(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl("https://translation.googleapis.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterface=retrofit.create(ApiService.class);
        }
        return apiInterface;
    }
}
