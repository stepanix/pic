package com.pic.service;


import android.support.annotation.NonNull;


public class PicServiceFactory {

    private PicServiceFactory() { /* default constructor */ }

    @NonNull
    public static IPicService getService() {
        return new PicService();
    }
}
