package com.pic.view.adapter;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.pic.R;
import com.pic.service.model.Photo;
import com.pic.view.viewholder.LoadingViewHolder;
import com.pic.view.viewholder.PhotoViewHolder;
import com.pic.view.viewinterface.IPhotoView;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Photo> photoList;
    private Context context;
    private IPhotoView onLoadMoreListener;
    private boolean isLoading;
    private int lastVisibleItem;
    private int totalItemCount;
    private int visibleThreshold = 5;
    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    public PhotoAdapter(Context context, IPhotoView onLoadMoreListener) {
        this.context = context;
        this.onLoadMoreListener = onLoadMoreListener;
        this.photoList = new ArrayList<>();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void reload() {
        onLoadMoreListener.onLoadMore();
        isLoading = true;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setItems(List<Photo> photoList) {
        if (photoList != null) {
            this.photoList.addAll(photoList);
            notifyItemInserted(photoList.size());
        }
        notifyDataSetChanged();
        setLoaded();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            return new PhotoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_layout, parent, false));
        } else if (viewType == VIEW_TYPE_LOADING) {
            return new LoadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_layout, parent, false));
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return photoList.size() - 1 == position ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PhotoViewHolder) {
            PhotoViewHolder photoViewHolder = (PhotoViewHolder) holder;
            Photo photo = photoList.get(position);
            Glide.with(context)
                    .load(photo.getImageUrl())
                    .into(photoViewHolder.getImageView());

            photoViewHolder.getTextView().setText(photo.getName());
        } else if (holder instanceof LoadingViewHolder) {
            if (isLoading) {
                ((LoadingViewHolder) holder).getProgressBar().setIndeterminate(true);
                ((LoadingViewHolder) holder).getProgressBar().setVisibility(View.VISIBLE);
            } else {
                ((LoadingViewHolder) holder).getProgressBar().setVisibility(View.GONE);
            }
        }
    }

}
