package com.pic.service.repo;


import android.support.annotation.NonNull;

import com.pic.service.PicServiceFactory;

public class PageRepoFactory {

    private PageRepoFactory() { /* default constructor */ }

    @NonNull
    public static IPageRepo getRepository() {
        return new PageRepo(PicServiceFactory.getService());
    }
}
