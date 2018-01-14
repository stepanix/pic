package com.pic.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pic.config.AppConfig;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class RestClient {

    private static RestClient service;

    private static RestInterface REST_INTERFACE;

    static{
        initRestArtifacts();
    }

    private static void initRestArtifacts()
    {
        Gson gson = new GsonBuilder()
                .create();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(AppConfig.SERVICE_URL)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(new OkHttpClient()))
                .build();
        REST_INTERFACE = restAdapter.create(RestInterface.class);
    }

    public RestClient(){
     //constructor
    }

    public static RestInterface getRestClient()
    {
        return REST_INTERFACE;
    }



}
