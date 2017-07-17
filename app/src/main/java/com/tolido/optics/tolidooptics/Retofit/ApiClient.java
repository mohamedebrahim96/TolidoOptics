package com.tolido.optics.tolidooptics.Retofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Home on 2017-07-16.
 */

public class ApiClient {
    public static final String url = "https://graph.facebook.com/tolido.net/feed?access_token=493383167666376|-jRi4AWvxsDu6EHRUEY73Kg_0z8&fields=full_picture,message";
    public static final String BASE_URL = "https://graph.facebook.com/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
