package com.pic.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pic.R;
import com.pic.service.model.Page;
import com.pic.service.repo.PageRepoFactory;
import com.pic.view.adapter.PhotoAdapter;
import com.pic.view.viewinterface.IPhotoView;
import com.pic.viewmodel.PhotoViewModel;

import java.io.IOException;


public class PhotoActivity extends AppCompatActivity implements IPhotoView {

    private static final String TAG = PhotoActivity.class.getSimpleName();

    private PhotoAdapter photoAdapter;
    private PhotoViewModel viewModel;
    private View progressViewLayout;
    private ImageView imageViewRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new PhotoViewModel(this, PageRepoFactory.getRepository());
        photoAdapter = new PhotoAdapter(this, this);

        RecyclerView photoRecyclerView = findViewById(R.id.photo_recycler_view);
        progressViewLayout = findViewById(R.id.layout_progress_bar);
        imageViewRefresh = findViewById(R.id.image_view_refresh);

        imageViewRefresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                refresh();
            }
        });

        progressViewLayout.setVisibility(View.VISIBLE);
        photoRecyclerView.setAdapter(photoAdapter);
        viewModel.loadPictures();
    }

    private void refresh() {
        progressViewLayout.setVisibility(View.VISIBLE);
        imageViewRefresh.setVisibility(View.GONE);
        viewModel.loadPictures();
    }

    @Override
    public void onGetPageSuccess(Page page) {
        photoAdapter.setItems(page.getPhotos());
        progressViewLayout.setVisibility(View.GONE);
    }

    @Override
    public void onGetPageError() {
        imageViewRefresh.setVisibility(View.VISIBLE);
        progressViewLayout.setVisibility(View.GONE);
        photoAdapter.setLoaded();
        Toast.makeText(this, getString(R.string.global_loading_error), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "Error processing page service");
    }

    @Override
    public void onLoadMore() {
        viewModel.loadPictures();
    }
}
