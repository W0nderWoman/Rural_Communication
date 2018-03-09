package com.example.gupta.ruralcommunication.LanguageFragment.Presenter;

import android.util.Log;

import com.example.gupta.ruralcommunication.LanguageFragment.Model.Data;
import com.example.gupta.ruralcommunication.LanguageFragment.Model.KeyConstants;
import com.example.gupta.ruralcommunication.LanguageFragment.Model.LanguageModel;
import com.example.gupta.ruralcommunication.LanguageFragment.Model.TranslationResponse;
import com.example.gupta.ruralcommunication.LanguageFragment.Model.Translations;
import com.example.gupta.ruralcommunication.Network.ApiClient;
import com.example.gupta.ruralcommunication.Network.ApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Vasudev on 3/9/2018.
 */

public class LanguagePresenter implements LanguagePresenterCalls {
    @Override
    public void getTranslatedText(String q) {
        ApiService apiService=ApiClient.getApiInterface();
        Call<TranslationResponse> call=apiService.getLanguageTranslation(q,"hi", KeyConstants.API_KEY);
        call.enqueue(new Callback<TranslationResponse>() {
            @Override
            public void onResponse(Call<TranslationResponse> call, Response<TranslationResponse> response) {
                if(response.body()!=null){
                    Log.d("response",response.toString());
                    Data data = response.body().data;
                    ArrayList<Translations> translationResponses = data.translations;
                    EventBus.getDefault().post(new LanguageModel(translationResponses));
                }
//                Log.d("response",response.body().toString());
//                //
//                if(response!=null) {
//                    assert (response.body().data !=null);
//                    Data data = response.body().data;
//                    ArrayList<Translations> translationResponses = data.translations;
//                    EventBus.getDefault().post(new LanguageModel(translationResponses));
//                }
            }

            @Override
            public void onFailure(Call<TranslationResponse> call, Throwable t) {
                Log.d("response",t.toString());
            }
        });
    }
}
