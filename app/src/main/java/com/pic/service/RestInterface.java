package com.pic.service;


import com.pic.service.model.Page;

import retrofit.http.GET;
import retrofit.http.Query;


public interface RestInterface {

    @GET("/photos")
    Page getPage(@Query("feature") String feature, @Query("consumer_key") String consumerKey);

}
