package com.pic.view.viewholder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.pic.R;

public class LoadingViewHolder extends RecyclerView.ViewHolder {

    private ProgressBar progressBar;

    public LoadingViewHolder(View view) {
        super(view);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
