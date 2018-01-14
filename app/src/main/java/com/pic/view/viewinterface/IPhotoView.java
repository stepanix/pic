package com.pic.view.viewinterface;


import com.pic.service.model.Page;

public interface IPhotoView {
    void onGetPageSuccess(Page page);
    void onGetPageError();
    void onLoadMore();
}
