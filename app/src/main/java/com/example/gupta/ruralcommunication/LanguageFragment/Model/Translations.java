package com.example.gupta.ruralcommunication.LanguageFragment.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vasudev on 3/9/2018.
 */

public class Translations {

    @SerializedName("translatedText")
    public String translatedText;

    @SerializedName("detectedSourceLanguage")
    public String detectedSourceLanguage;
}
