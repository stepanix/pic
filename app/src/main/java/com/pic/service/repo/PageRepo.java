package com.pic.service.repo;


import com.pic.service.IPicService;
import com.pic.service.model.Page;


public class PageRepo implements IPageRepo {

    private IPicService service;

    public PageRepo(IPicService service) {
        this.service = service;
    }

    @Override
    public Page getPage(String feature, String consumerKey) {
        return service.getPage(feature, consumerKey);
    }
}
