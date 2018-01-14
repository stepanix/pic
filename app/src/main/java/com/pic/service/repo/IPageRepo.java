package com.pic.service.repo;


import com.pic.service.model.Page;


public interface IPageRepo {
    Page getPage(String feature, String consumerKey) throws Exception;
}
