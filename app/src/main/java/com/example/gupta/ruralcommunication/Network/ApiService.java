package com.example.gupta.ruralcommunication.Network;

import com.example.gupta.ruralcommunication.LanguageFragment.Model.TranslationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Vasudev on 3/9/2018.
 */

public interface ApiService {
    @GET("language/translate/v2")
    Call<TranslationResponse>  getLanguageTranslation(@Query("q") String q,@Query("target") String target,@Query("key") String key);

}
