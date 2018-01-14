package com.pic.service;


import com.pic.service.model.Page;


public class PicService implements IPicService {


    @Override
    public Page getPage(String feature, String consumerKey) {
        return RestClient.getRestInterface().getPage(feature,consumerKey);
    }
}
