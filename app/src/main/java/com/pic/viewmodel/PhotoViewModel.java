package com.pic.viewmodel;


import android.os.AsyncTask;

import com.pic.config.AppConfig;
import com.pic.service.model.Page;
import com.pic.service.repo.IPageRepo;
import com.pic.view.viewinterface.IPhotoView;

public class PhotoViewModel {

    private IPhotoView view;
    private IPageRepo repo;

    public PhotoViewModel(IPhotoView view, IPageRepo repo) {
        this.view = view;
        this.repo = repo;
    }

    public void loadPictures() {
        new PhotoViewModel.GetPageAsyncTask().execute();
    }

    private class GetPageAsyncTask extends AsyncTask<Void, Void, Page> {

        @Override
        protected Page doInBackground(Void... params) {
            try {
                return repo.getPage(AppConfig.API_FEATURE_FRESH_TODAY, AppConfig.CONSUMER_KEY);
            } catch (Exception ex) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Page result) {
            super.onPostExecute(result);
            if (result != null) {
                view.onGetPageSuccess(result);
            } else {
                view.onGetPageError();
            }
        }

    }

}
