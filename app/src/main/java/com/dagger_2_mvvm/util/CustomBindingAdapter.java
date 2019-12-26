package com.dagger_2_mvvm.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dagger_2_mvvm.R;

import android.databinding.BindingAdapter;

public class CustomBindingAdapter {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url != null) {
            RequestOptions options = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
            Glide.with(imageView.getContext())
                    .setDefaultRequestOptions(options)
                    .load(url)
                    .into(imageView);
        }
    }

}
